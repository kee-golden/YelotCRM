package com.soecode.wxtools.api;

import com.alibaba.fastjson.JSON;
import com.soecode.wxtools.bean.*;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.*;
import com.soecode.wxtools.bean.result.card.Card;
import com.soecode.wxtools.bean.result.card.WxCardResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.DateUtil;
import com.soecode.wxtools.util.PayUtil;
import com.soecode.wxtools.util.RandomUtils;
import com.soecode.wxtools.util.crypto.JSSDK_Config;
import com.soecode.wxtools.util.crypto.SHA1;
import com.soecode.wxtools.util.crypto.WxSign;
import com.soecode.wxtools.util.file.FileUtils;
import com.soecode.wxtools.util.http.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 统一业务处理类
 * @author antgan
 * @date 2016/12/14
 * 
 */
public class WxService implements IService{
	//全局的是否正在刷新access token的锁
	protected static final Object globalAccessTokenRefreshLock = new Object();
	//全局的是否正在刷新jsapi_ticket的锁
	protected static final Object globalJsapiTicketRefreshLock = new Object();
	//全局的是否正在刷新 卡券 api_ticket的锁
	protected static final Object globalCardJsapiTicketRefreshLock = new Object();
	//HttpClient
	protected CloseableHttpClient httpClient;
	
	/**
	 * 构造方法，初始化httpClient
	 */
	public WxService() {
		httpClient = HttpClients.createDefault();
	}

	/*****************************
	 *                           *
	 *    以下为微信公众号API接口     *
	 *                           *
	 *****************************/
	
	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
		try {
			return SHA1.gen(WxConfig.getInstance().getToken(), timestamp, nonce).equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getAccessToken() throws WxErrorException {
		return getAccessToken(false);
	}

	@Override
	public String getAccessToken(boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			WxConfig.getInstance().expireAccessToken();
		}
		if (WxConfig.getInstance().isAccessTokenExpired()) {
			synchronized (globalAccessTokenRefreshLock) {
				if (WxConfig.getInstance().isAccessTokenExpired()) {
					String url = WxConsts.URL_GET_ACCESSTOEKN.replace("APPID", WxConfig.getInstance().getAppId())
							.replace("APPSECRET", WxConfig.getInstance().getAppSecret());
					try {
						String resultContent = get(url, null);
						WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
						WxConfig.getInstance().updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
						System.out.println("[wx-tools]update accessToken success. accessToken:"+accessToken.getAccess_token());
					} catch (IOException e) {
						throw new WxErrorException("[wx-tools]refresh accessToken failure.");
					}
				}
			}
		}
		System.out.println("access token:"+WxConfig.getInstance().getAccessToken());
		return WxConfig.getInstance().getAccessToken();
	}

	@Override
	public String[] getCallbackIp() throws WxErrorException {
		String[] ips = null;
		String url = WxConsts.URL_GET_WX_SERVICE_IP.replace("ACCESS_TOKEN", getAccessToken());
		String responseContent = get(url, null);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(responseContent);
			ips = mapper.readValue(node.get("ip_list"), String[].class);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getCallbackIp failure.");
		}
		return ips;
	}

	@Override
	public String createMenu(WxMenu menu, boolean condition) throws WxErrorException {
		String url = null, result = null;
		if (condition)
			url = WxConsts.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken());
		else
			url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", getAccessToken());

		try {
			result = post(url, menu.toJson());
			System.out.println("[wx-tools]Create Menu result:" + result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createMenu failure.");
		}
		return result;
	}

	@Override
	public String deleteMenu() throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String result = get(url, null);
		System.out.println("[wx-tools]Delete Menu result:" + result);
		return result;
	}

	@Override
	public String deleteMenu(String menuid) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken());

		String json = "{" + "\"menuid\":" + menuid + "}";
		String result = post(url, json);
		System.out.println("[wx-tools]Delete Conditional Menu result:" + result);
		return result;
	}

	@Override
	public WxMenuResult getMenu() throws WxErrorException {
		String url = WxConsts.URL_GET_MENU.replace("ACCESS_TOKEN", getAccessToken());
		WxMenuResult result = null;
		try {
			result = WxMenuResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenu failure.");
		}
		return result;
	}

	@Override
	public WxCurMenuInfoResult getMenuCurInfo() throws WxErrorException {
		String url = WxConsts.URL_GET_CURRENT_MENU_INFO.replace("ACCESS_TOKEN", getAccessToken());
		WxCurMenuInfoResult result = null;
		try {
			result = WxCurMenuInfoResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenuCurInfo failure.");
		}
		return result;
	}

	@Override
	public String menuTryMatch(String user_id) throws WxErrorException {
		String url = WxConsts.URL_TRYMATCH_MENU.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"user_id\":\"" + user_id + "\"" + "}";
		return post(url, json);
	}

	@Override
	public WxMediaUploadResult uploadTempMedia(String mediaType, String fileType, InputStream inputStream)
			throws WxErrorException, IOException {
		return uploadTempMedia(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
	}

	@Override
	public WxMediaUploadResult uploadTempMedia(String mediaType, File file) throws WxErrorException {
		String url = WxConsts.URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE", mediaType);
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	public File downloadTempMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("MEDIA_ID",
				media_id);
		return execute(new MediaDownloadGetRequestExecutor(path), url, null);
	}

	@Override
	public WxMediaUploadResult uploadMedia(String mediaType, String fileType, InputStream inputStream,
			WxVideoIntroduction introduction) throws WxErrorException, IOException {
		return uploadMedia(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType),
				introduction);
	}

	@Override
	public WxMediaUploadResult uploadMedia(String mediaType, File file, WxVideoIntroduction introduction)
			throws WxErrorException {
		WxMediaUploadResult result = null;
		String url = WxConsts.URL_UPLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken()).replace("TYPE",
				mediaType);
		// 如果是视频素材，添加视频描述对象
		if (WxConsts.MEDIA_VIDEO.equals(mediaType)) {
			result = execute(new MediaUploadRequestExecutor(introduction), url, file);
		} else {
			result = execute(new MediaUploadRequestExecutor(), url, file);
		}
		return result;
	}

	@Override
	public File downloadMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new MediaDownloadPostRequestExecutor(path), url, json);
	}

	@Override
	public WxNewsMediaResult downloadNewsMedia(String media_id) throws WxErrorException {
		WxNewsMediaResult newsResult = null;
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		try {
			newsResult = WxNewsMediaResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]downloadNewsMedia failure.");
		}
		return newsResult;
	}

	@Override
	public WxVideoMediaResult downloadVideoMedia(String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new VideoDownloadPostRequestExecutor(path), url, json);
	}

	@Override
	public WxError deleteMediaMaterial(String media_id) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		WxError err = null;
		try {
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteMediaMaterial failure.");
		}
		return err;
	}

	@Override
	public String addNewsMedia(List<WxNewsInfo> news) throws WxErrorException {
		String media_id = null;
		String url = WxConsts.URL_ADD_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String arrayJson = mapper.writeValueAsString(news);
			String json = "{\"articles\":" + arrayJson + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			JsonNode node = mapper.readTree(result);
			media_id = node.get("media_id").asText();
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]addNewsMedia failure.");
		}
		return media_id;
	}

	@Override
	public WxMediaUploadResult imageDomainChange(File file) throws WxErrorException {
		String url = WxConsts.URL_IMAGE_DOMAIN_CHANGE.replace("ACCESS_TOKEN", getAccessToken());
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	@Override
	public WxError updateNewsInfo(String media_id, int index, WxNewsInfo newInfo) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken());

		try {
			String json = "{" + "\"media_id\":" + "\"" + media_id + "\"," + "\"index\":" + index + "," + "\"articles\":"
					+ newInfo.toJson() + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateNewsInfo failure.");
		}
		return err;
	}

	@Override
	public WxMaterialCountResult getMaterialCount() throws WxErrorException {
		String url = WxConsts.URL_GET_MATERIAL_COUNT.replace("ACCESS_TOKEN", getAccessToken());
		WxMaterialCountResult result = null;
		try {
			result = WxMaterialCountResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMaterialCount failure.");
		}
		return result;
	}

	@Override
	public WxBatchGetMaterialResult batchGetMeterial(String type, int offset, int count) throws WxErrorException {
		String url = WxConsts.URL_BATCHGET_MATERIAL_MEDIA_LIST.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{" + "\"type\":\"" + type + "\"," + "\"offset\":" + offset + "," + "\"count\":" + count + "}";
		String result = post(url, json);
		WxBatchGetMaterialResult materialResult = null;
		try {
			materialResult = WxBatchGetMaterialResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetMeterial failure.");
		}
		return materialResult;
	}

	@Override
	public WxUserGroupResult createUserGroup(String name) throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_CREATE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"group\":{\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			result = WxUserGroupResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createUserGroup failure.");
		}
		return result;
	}

	@Override
	public WxUserGroupResult queryAllUserGroup() throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_QUERY_ALL_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String getResult = get(url, null);
		try {
			result = WxUserGroupResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryAllUserGroup failure.");
		}
		return result;
	}

	@Override
	public int queryGroupIdByOpenId(String openid) throws WxErrorException {
		int result = -1;
		String url = WxConsts.URL_QUERY_USER_GROUP_BY_OPENID.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(postResult);
			result = Integer.parseInt(node.get("groupid").toString());
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryGroupIdByOpenId failure.");
		}
		return result;
	}

	@Override
	public WxError updateUserGroupName(int groupid, String name) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_GROUP_NAME.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"group\":{\"id\":" + groupid + ",\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserGroupName failure.");
		}
		return err;
	}

	@Override
	public WxError movingUserToNewGroup(String openid, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\",\"to_groupid\":" + to_groupid + "}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]movingUserToNewGroup failure.");
		}
		return err;
	}

	@Override
	public WxError batchMovingUserToNewGroup(List<String> openids, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_BATCH_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(openids);
			String json = "{\"openid_list\":" + arrayJson + ",\"to_groupid\":" + to_groupid + "}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchMovingUserToNewGroup failure.");
		}
		return err;
	}

	@Override
	public WxError deleteUserGroup(int groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_DELETE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String json = "{\"group\":{\"id\":" + groupid + "}}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteUserGroup failure.");
		}
		return err;
	}

	@Override
	public WxError updateUserRemark(String openid, String remark) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_REMARK.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"openid\":\"" + openid + "\",\"remark\":\"" + remark + "\"}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserRemark failure.");
		}
		return err;
	}

	@Override
	public WxUser getUserInfoByOpenId(WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken())
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String postResult = post(url, null);
		try {
			user = WxUser.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getUserInfoByOpenId failure.");
		}
		return user;
	}

	@Override
	public WxUserList batchGetUserInfo(List<WxUserGet> usersGet) throws WxErrorException {
		WxUserList list = null;
		String url = WxConsts.URL_BATCH_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(usersGet);
			String json = "{\"user_list\":" + arrayJson + "}";
			String postResult = post(url, json);
			list = WxUserList.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserInfo failure.");
		}
		return list;
	}

	@Override
	public WxUserListResult batchGetUserOpenId(String next_openid) throws WxErrorException {
		WxUserListResult result = null;
		String url = WxConsts.URL_BATCH_GET_USER_OPENID.replace("ACCESS_TOKEN", getAccessToken()).replace("NEXT_OPENID",
				next_openid);
		String getResult = get(url, null);
		try {
			result = WxUserListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserOpenId failure.");
		}
		return result;
	}

	@Override
	public String oauth2buildAuthorizationUrl(String redirectUri, String scope, String state) {
		redirectUri = URIUtil.encodeURIComponent(redirectUri);
		String url = WxConsts.URL_OAUTH2_GET_CODE.replace("APPID", WxConfig.getInstance().getAppId())
				.replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope).replace("STATE", state);
		return url;
	}

	@Override
	public WxOAuth2AccessTokenResult oauth2ToGetAccessToken(String code) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_ACCESSTOKEN.replace("APPID", WxConfig.getInstance().getAppId())
				.replace("SECRET", WxConfig.getInstance().getAppSecret()).replace("CODE", code);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetAccessToken failure.");
		}
		return result;
	}

	@Override
	public WxOAuth2AccessTokenResult oauth2ToGetRefreshAccessToken(String refresh_token) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_REFRESH_ACCESSTOKEN.replace("APPID", WxConfig.getInstance().getAppId())
				.replace("REFRESH_TOKEN", refresh_token);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetRefreshAccessToken failure.");
		}
		return result;
	}

	@Override
	public WxUser oauth2ToGetUserInfo(String access_token, WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_OAUTH2_GET_USER_INFO.replace("ACCESS_TOKEN", access_token)
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String getResult = get(url, null);
		try {
			user = WxUser.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetUserInfo failure.");
		}
		return user;
	}

	@Override
	public WxError oauth2CheckAccessToken(String access_token, String openid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_OAUTH2_CHECK_ACCESSTOKEN.replace("ACCESS_TOKEN", access_token).replace("OPENID",
				openid);
		String getResult = get(url, null);
		try {
			err = WxError.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2CheckAccessToken failure.");
		}
		return err;
	}

	@Override
	public QrCodeResult createQrCode(WxQrcode qrcode) throws WxErrorException {
		QrCodeResult result = null;
		String url = WxConsts.URL_GET_QR_CODE.replace("TOKEN", getAccessToken());
		String json = null;
		try {
			json = qrcode.toJson();
			String postResult = post(url, json);
			result = QrCodeResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createQrCode failure.");
		}
		return result;
	}

	@Override
	public File downloadQrCode(File dir, String ticket) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_QR_CODE.replace("TICKET", URIUtil.encodeURIComponent(ticket));
		return execute(new QrCodeDownloadGetRequestExecutor(dir), url, null);
	}

	@Override
	public String getShortUrl(String long_url) throws WxErrorException {
		String url = WxConsts.URL_LONGURL_TO_SHORTURL.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"action\":\"long2short\",\"long_url\":\"" + long_url + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getShortUrl failure.");
		}
		String shortUrl = node.get("short_url").asText();
		return shortUrl;
	}

	/**
	 * 采用http GET方式请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）：
	 * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
	 * @return
	 * @throws WxErrorException
	 */
	public String getJsapiTicket() throws WxErrorException {
		return getJsapiTicket(false);
	}

	public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			WxConfig.getInstance().expireJsapiTicket();
		}
		if (WxConfig.getInstance().isJsapiTicketExpired()) {
			synchronized (globalJsapiTicketRefreshLock) {
				if (WxConfig.getInstance().isJsapiTicketExpired()) {
					String url = WxConsts.URL_GET_JS_API_TICKET.replace("ACCESS_TOKEN", getAccessToken());
					String responseContent = execute(new SimpleGetRequestExecutor(), url, null);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(responseContent);
						if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
							WxError error = WxError.fromJson(responseContent);
							throw new WxErrorException(error);
						}
						String jsapiTicket = node.get("ticket").asText();
						int expiresInSeconds = node.get("expires_in").asInt();
						WxConfig.getInstance().updateJsapiTicket(jsapiTicket, expiresInSeconds);
						System.out.println("[wx-tools]update jsapiTicket success. ticket: "+jsapiTicket);
					} catch (Exception e) {
						throw new WxErrorException("[wx-tools]getJsapiTicket failure.");
					}
				}
			}
		}
		return WxConfig.getInstance().getJsapiTicket();
	}

	/**
	 * 获取卡券ticket
	 * @return
	 * @throws WxErrorException
	 */
	public String getCardJsapiTicket() throws WxErrorException {
//		if (forceRefresh) {
//			WxConfig.getInstance().expireJsapiTicket();
//		}
		if (WxConfig.getInstance().isCardApiTicketExpired()) {
			synchronized (globalCardJsapiTicketRefreshLock) {
				if (WxConfig.getInstance().isCardApiTicketExpired()) {
					String url = WxConsts.URL_CARD_API_TICKET.replace("ACCESS_TOKEN", getAccessToken());
					String responseContent = execute(new SimpleGetRequestExecutor(), url, null);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(responseContent);
						if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
							WxError error = WxError.fromJson(responseContent);
							throw new WxErrorException(error);
						}
						String cardApiTicket = node.get("ticket").asText();
						int expiresInSeconds = node.get("expires_in").asInt();//7200
						WxConfig.getInstance().updateCardJsapiTicket(cardApiTicket, expiresInSeconds);
						System.out.println("[wx-tools]update jsapiTicket success. ticket: "+cardApiTicket);
					} catch (Exception e) {
						throw new WxErrorException("[wx-tools]getJsapiTicket failure.");
					}
				}
			}
		}
		return WxConfig.getInstance().getCardApiTicket();
	}

	/**
	 * 获取前端jsapiConfig
	 * @param url 调用api的页面url
	 * @param jsApiList 需要调用的api，详情见附录二【http://mp.weixin.qq.com/wiki/11/74ad127cc054f6b80759c40f77ec03db.html】
	 * @return
	 * @throws WxErrorException
	 */
	public WxJsapiConfig createJsapiConfig(String url, List<String> jsApiList) throws WxErrorException {
		long timestamp = System.currentTimeMillis() / 1000;
		String noncestr = RandomUtils.getRandomStr(16);
		String jsapiTicket = getJsapiTicket();
		try {
			String signature = SHA1.genWithAmple("noncestr="+noncestr,
					"jsapi_ticket="+jsapiTicket,"timestamp="+timestamp,"url="+url);

			WxJsapiConfig jsapiConfig = new WxJsapiConfig();
			jsapiConfig.setAppid(WxConfig.getInstance().getAppId());
			jsapiConfig.setTimestamp(timestamp);
			jsapiConfig.setNoncestr(noncestr);
			jsapiConfig.setUrl(url);
			jsapiConfig.setSignature(signature);
//			jsapiConfig.setJsApiList(jsApiList);
			System.out.println("kee jsConfig:"+JSON.toJSONString(jsapiConfig));
			return jsapiConfig;
		} catch (NoSuchAlgorithmException e) {
			throw new WxErrorException("[wx-tools]createJsapiConfig failure.");
		}
	}

	public WxJsapiConfig getJsConfig(String url){
		JSSDK_Config config = new JSSDK_Config();
		try {
			HashMap<String, String> map = config.jsSDK_Sign(url);
		}catch (Exception e){
			e.printStackTrace();
		}
		return  null;

	}

	/**
	 * 卡券 签名(其中code,openid 为不是必填项)
	 * 将 api_ticket、timestamp、card_id、code、openid、nonce_str的value值进行字符串的字典序排序
	 * 参考：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115
	 * @return
	 * @throws WxErrorException
	 */
	public WxCardApiSignature createCardApiConfig(String code,String card_id,String openid){
		long timestamp = System.currentTimeMillis() / 1000;
		String noncestr = RandomUtils.getRandomStr(16);
		String cardApiTicket = null;
		try {
			cardApiTicket = getCardJsapiTicket();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		WxSign wxCardSign = new WxSign();
			wxCardSign.addData(timestamp);
			wxCardSign.addData(noncestr);
			//签名的参数
//			wxCardSign.addData(cardApiTicket);
			if(code != null){
				wxCardSign.addData(code);
			}
			if(card_id != null){
				wxCardSign.addData(card_id);
			}
			if(openid != null){
				wxCardSign.addData(openid);
			}

			String signature = wxCardSign.GetSignature();
			WxCardApiSignature wxCardApiSignature = new WxCardApiSignature();
			wxCardApiSignature.setTimestamp(timestamp);
			wxCardApiSignature.setNoncestr(noncestr);
			wxCardApiSignature.setOpenid(openid);
			wxCardApiSignature.setCode(code);
			wxCardApiSignature.setCard_id(card_id);
			wxCardApiSignature.setSignature(signature);
			wxCardApiSignature.setCardApiTicket(cardApiTicket);
			return wxCardApiSignature;

	}
	
	@Override
	public SenderResult sendAllByGroup(WxGroupSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_GROUP_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByGroup failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllByOpenid(WxOpenidSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_OPENID_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByOpenid failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllPreview(PreviewSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_PREVIEW_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllPreview failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllDelete(String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":"+msg_id+"}";
		String url = WxConsts.URL_DELETE_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllDelete failure.");
		}
		return result;
	}
	
	@Override
	public SenderResult sendAllGetStatus(String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":\""+msg_id+"\"}";
		String url = WxConsts.URL_GET_STATUS_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken());
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllGetStatus failure.");
		}
		return result;
	}
	
	@Deprecated
	@Override
	public InvokePay unifiedOrder(PayOrderInfo order, String notifyUrl, String openid) throws WxErrorException{
		InvokePay ivp = new InvokePay();
		WxUnifiedOrder payinfo = PayUtil.createPayInfo(order, notifyUrl, openid);
		String postResult = null;
		try {
			postResult = post(WxConsts.URL_PAY_UNIFIEORDER, payinfo.toXml());
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(postResult);
		
		UnifiedOrderResult result = UnifiedOrderResult.fromXml(postResult);
		
		//赋值
		ivp.setAppId(result.getAppid());
		ivp.setNonceStr(result.getNonceStr());
		ivp.setPaySign(result.getSign());
		ivp.setPrepayId(result.getPrepayId());
		ivp.setSignType("MD5");
		ivp.setTimeStamp(DateUtil.getTimestamp());
		
		//拼接map
		Map<String,String> map = new HashMap<>();
		map.put("appId", ivp.getAppId());
		map.put("timeStamp", ivp.getTimeStamp());
		map.put("nonceStr", ivp.getNonceStr());
		map.put("package", "prepay_id="+ivp.getPrepayId());
		map.put("signType", ivp.getSignType());
		ivp.setPaySign(PayUtil.createSign(map, WxConfig.getInstance().getApiKey()));;
		return ivp;
	}
	
	@Override
	public WxError templateSetIndustry(String industry1, String industry2) throws WxErrorException {
		WxError result = null;
		String url = WxConsts.URL_TEMPLATE_SET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"industry_id1\":\""+industry1+"\",\"industry_id2\":\""+industry2+"\"}";
		try {
			String postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSetIndustry failure.");
		}
		return result;
	}
	
	@Override
	public IndustryResult templateGetIndustry() throws WxErrorException {
		IndustryResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken());
		try {
			getResult = get(url, null);
			result = IndustryResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetIndustry failure.");
		}
		return result;
	}
	
	@Override
	public TemplateResult templateGetId(String template_id_short) throws WxErrorException {
		TemplateResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_ID.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"template_id_short\":\""+template_id_short+"\"}";
		try {
			postResult = post(url, json);
			result = TemplateResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetId failure.");
		}
		return result;
	}
	
	@Override
	public TemplateListResult templateGetList() throws WxErrorException {
		TemplateListResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_LIST.replace("ACCESS_TOKEN", getAccessToken());
		try {
			getResult = get(url, null);
			result = TemplateListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetList failure.");
		}
		return result;
	}
	
	@Override
	public WxError templateDelete(String template_id) throws WxErrorException {
		WxError result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_DELETE.replace("ACCESS_TOKEN", getAccessToken());
		String json = "{\"template_id\":\""+template_id+"\"}";
		try {
			postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateDelete failure.");
		}
		return result;
	}
	
	@Override
	public TemplateSenderResult templateSend(TemplateSender sender) throws WxErrorException {
		TemplateSenderResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN", getAccessToken());
		try {
			postResult = post(url, sender.toJson());
			result = TemplateSenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSend failure.");
		}
		return result;
	}

	/**
	 * 优惠券相关接口,add by kee
	 * 参数：
	 * CARD_STATUS_NOT_VERIFY”,待审核；
	 “CARD_STATUS_VERIFY_FAIL”,审核失败；“CARD_STATUS_VERIFY_OK”，通过审核；
	 “CARD_STATUS_DELETE”，卡券被商户删除；
	 “CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
	 *
	 {
	 "offset": 0,
	 "count": 100,
	 "status_list": ["CARD_STATUS_VERIFY_OK", "CARD_STATUS_DISPATCH"]
	 }
	 * @return
	 */

	public List<Card> getCardList(){
		String postResult = null;
		WxCardList wxCardList = null;
		List<Card> cardList = new ArrayList<Card>();
		try {
			String url = WxConsts.URL_CARD_IDS_GET_LIST.replace("ACCESS_TOKEN", getAccessToken());
			String params = "{\"offset\":0,\"count\":20,\"status_list\": [ \"CARD_STATUS_DISPATCH\"]}";
			postResult = post(url,params);
//			System.out.println("kee card:"+postResult);
			wxCardList = JSON.parseObject(postResult,WxCardList.class);
			if(wxCardList != null && wxCardList.getCard_id_list() != null){
				String cardUrl = WxConsts.URL_CARD_GET__LIST.replace("ACCESS_TOKEN", getAccessToken());

				List<String> cardIds = wxCardList.getCard_id_list();
				String result = null;
				for (int i = 0; i < cardIds.size(); i++) {
					String cardId = cardIds.get(i);
					String cardParams = "{" +
							"\"card_id\":\""+cardId+"\"" +
							"}";
					result = post(cardUrl,cardParams);
//					System.out.println("kee result:"+result);
					WxCardResult cardResult = JSON.parseObject(result,WxCardResult.class);
					cardList.add(cardResult.getCard());
				}
			}

		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return cardList;

	}






	protected CloseableHttpClient getHttpclient() {
		return this.httpClient;
	}

	public String get(String url, Map<String, String> params) throws WxErrorException {
		return execute(new SimpleGetRequestExecutor(), url, params);
	}

	public String post(String url, String params) throws WxErrorException {
		return execute(new SimplePostRequestExecutor(), url, params);
	}

	/**
	 * 
	 * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
	 *
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException{
		try {
			return executeInternal(executor, uri, data);
		} catch (WxErrorException e) {
			throw e;
		}
	}

	/**
	 * 请求执行器
	 * 
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	protected synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data)
			throws WxErrorException{
		try {
			return executor.execute(getHttpclient(), uri, data);
		} catch (WxErrorException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
