<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="zh-cn">
<head>
<style>
</style>
</head>
<body>
	<div id="repairOrderDetail">
		<div id="titleDiv">
			<div class="col-xs-8">
				<div class="pull-left">
					<img src="${ctx}/img/printLogo.png" style="height: 78px">
					<h5 style="float: right; line-height: 25px; margin: 3px; font-family: 宋体; font-weight: normal;">
						御金匠${repairOrder.firstCategoryName}维修单登记表<br> 地址：${repairOrder.shopAddress}<br> 电话：4009611966&nbsp;&nbsp;&nbsp;${repairOrder.shopPhone}
					</h5>
				</div>
			</div>
			<div class="col-xs-4" style="line-height: 60px;">
				<h5 style="float: right; line-height: 25px; margin: 3px; font-family: 宋体; font-weight: normal;">
					维修单号：${repairOrder.orderNo}<br> 开单日期：
					<fmt:formatDate value="${repairOrder.createAt}" pattern="yyyy-MM-dd" />
					<br> 经手人：${repairOrder.createUserName}
				</h5>
			</div>
		</div>
		<hr id="customerHr" color="black" width="100%" style="height: 1px; border: 1px solid black; margin: 0px">
		<div id="customerDiv" class="col-xs-4" style="width: 100%">
			<h4 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">客户信息</h4>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td width="25%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">客户姓名：</h5></td>
					<td width="25%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.customerName}</h5></td>
					<td width="25%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">联系方式：</h5></td>
					<td width="25%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.customerPhone}</h5></td>
				</tr>
				<tr>
					<td width="25%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">其他联系方式：</h5></td>
					<td width="25%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.customerPhoneSecond}</h5></td>
					<td width="25%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">回寄地址：</h5></td>
					<td width="25%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.customerAddress}</h5></td>
				</tr>
			</table>
		</div>
		<hr id="productHr" color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="productDiv" class="col-xs-4" style="width: 100%; height: 780px">
			<h4 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">产品信息</h4>
			<br>
			<table id="productTable" style="width: 100%">
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">分类：</h5></td>
					<td width="12%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.firstCategoryName}</h5></td>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">类型：</h5></td>
					<td width="12%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.secondCategoryName}</h5></td>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">品牌：</h5></td>
					<td width="12%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.brandName}</h5></td>
					<td width="12%"></td>
					<td width="12%"></td>
				</tr>
				<c:forEach items="${repairOrder.productInfoList}" var="item">
					<c:if test="${item.id%4==1}">
						<tr>
					</c:if>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">${item.attributeName}：</h5></td>
					<td width="12%"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${item.selectionValues}</h5></td>
					<c:if test="${item.id%4==0}">
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">外观描述：</h5></td>
					<td width="84%" colspan="7"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.imageDesc}</h5></td>
				</tr>
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">产品附图：</h5></td>
					<td width="84%" colspan="7">
						<table style="width: 100%; height: 530px; border: solid 1px black;">
							<c:forEach items="${repairOrder.imagesList}" var="item" varStatus="status">
								<c:if test="${status.index%4==0}">
									<tr height="132px">
								</c:if>
								<td width="22%"><img src="${item}" width="100%" height="132px" style="padding: 2px"></td>
								<c:if test="${status.index%4==3}">
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${repairOrder.imagesList.size()%4==1}">
								<td width="66%" colspan="3"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()%4==2}">
								<td width="44%" colspan="2"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()%4==3}">
								<td width="22%"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()<=4}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()>4 && repairOrder.imagesList.size()<=8}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()>8 && repairOrder.imagesList.size()<=12}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
				<tr id="precheckImagesList">
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">预检附图：</h5></td>
					<td width="84%" colspan="7">
						<table style="width: 100%; height: 530px; border: solid 1px black;">
							<c:forEach items="${repairOrder.precheckImagesList}" var="item" varStatus="status">
								<c:if test="${status.index%4==0}">
									<tr height="132px">
								</c:if>
								<td width="22%"><img src="${item}" width="100%" height="132px" style="padding: 2px"></td>
								<c:if test="${status.index%4==3}">
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${repairOrder.precheckImagesList.size()%4==1}">
								<td width="66%" colspan="3"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList.size()%4==2}">
								<td width="44%" colspan="2"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList.size()%4==3}">
								<td width="22%"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList==null}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList.size()<=4}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList.size()>4 && repairOrder.precheckImagesList.size()<=8}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.precheckImagesList.size()>8 && repairOrder.precheckImagesList.size()<=12}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
				<tr id="qccheckImagesList">
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">修完附图：</h5></td>
					<td width="84%" colspan="7">
						<table style="width: 100%; height: 530px; border: solid 1px black;">
							<c:forEach items="${repairOrder.qccheckImagesList}" var="item" varStatus="status">
								<c:if test="${status.index%4==0}">
									<tr height="132px">
								</c:if>
								<td width="22%"><img src="${item}" width="100%" height="132px" style="padding: 2px"></td>
								<c:if test="${status.index%4==3}">
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${repairOrder.qccheckImagesList.size()%4==1}">
								<td width="66%" colspan="3"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList.size()%4==2}">
								<td width="44%" colspan="2"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList.size()%4==3}">
								<td width="22%"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList==null}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList.size()<=4}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList.size()>4 && repairOrder.qccheckImagesList.size()<=8}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
							<c:if test="${repairOrder.qccheckImagesList.size()>8 && repairOrder.qccheckImagesList.size()<=12}">
								<tr height="132px">
									<td width="88%" colspan="4"></td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">维修内容：</h5></td>
					<td width="84%" colspan="7"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.repairDesc}</h5></td>
				</tr>
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">服务项：</h5></td>
					<td width="84%" colspan="7"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.serviceItemNames}</h5></td>
				</tr>
				<tr>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">维修费用：</h5></td>
					<td width="36%" colspan="3"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">${repairOrder.totalPayment}</h5></td>
					<td width="12%"><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">维修工期：</h5></td>
					<td width="36%" colspan="3"><h5 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">
							<fmt:formatDate value="${repairOrder.pickupAt}" pattern="yyyy-MM-dd" />
						</h5></td>
				</tr>
			</table>
		</div>
		<hr id="remarkHr" color="black" width="100%" style="height: 1px; margin: 0px; display: none">
		<div id="remarkDiv" class="col-xs-4" style="width: 100%; display: none">
			<h4 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">备注信息</h4>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（1）请妥善保管本单原件作为日后取件凭证</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（2）请您在签字前阅读本单据正后面之全部内容和条款</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（3）如有预计工期延迟会提前通知客户</h5></td>
				</tr>
				<tr>
					<td><h5 align="center" style="margin: 3px; font-family: 宋体; font-weight: normal;">您的签字表示您已经仔细阅读，充分理解并且接受该等条款</h5></td>
				</tr>
			</table>
		</div>
		<hr id="clauseHr" color="black" width="100%" style="height: 1px; margin: 0px; display: none">
		<div id="clauseDiv" class="col-xs-4" style="width: 100%; display: none">
			<h4 style="float: left; margin: 3px; font-family: 宋体; font-weight: normal;">一般条款与条件</h4>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（1）接收维修货品前必须由顾客签字确认，并附上一次性条码，御金匠对于顾客的维修货品不作任何鉴定。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（2）您特此授权御金匠在商品维修过程中采取任何必要且御金匠认为合适的方式执行维修。御金匠将相应安排维修，但商品是否可恢复其原厂出厂状态还取决于您使用的年限及商品的损伤程度。其中补色工艺后皮质的手感和颜色会略有差异，属于正常现象。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（3）如果经御金匠确认该商品无法维修，或者您未能在御金匠告知维修费用报价之日起（7日）内确认维修报价，御金匠会将未予维修的商品按起始状态退还与您。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（4）维修商品经维修完成且已支付全额维修费用（若有）后，御金匠会将商品归还给持有该维修单之人，该维修单的持有人被视为商品的合格所有者。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（5）本维修单原件为日后领取维修商品的唯一凭证，遗失不补，复印无效，请妥善保管。如果领取维修商品时，不能提供该维修单，为保障您的消费权益，御金匠保留要求核实身份证及暂存其复印件的权利。以证实顾客身份。代领维修件时，为保障您的消费权益，要求代领者必须持有本维修单原件并要求出示代领者本人身份证或其他有效证件（护照、机动车驾驶证等），御金匠有权暂存其复印件、登记身份信息和留下有效通讯（移动电话或座机）。并提供顾客本人预留的相关身份信息（如身份证号、护照号、机动车驾驶证号）以保障您的消费权益。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（6）如果收到领取维修商品通知后，顾客须按通知规定时间领取维修商品。顾客若逾期未领取维修商品，则御金匠有权收取相应的费用。若顾客逾期（三个月）未领取商品，顾客则自行承担商品的毁损灭失风险。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">
							（7）如果因为以下情形造成任何损失，御金匠无需承担责任：<br>&nbsp;&nbsp;&nbsp;&nbsp;A）御金匠拒绝维修，而顾客强行要求维修，御金匠对此作出是否维修的决定。<br>&nbsp;&nbsp;&nbsp;&nbsp;B）顾客采取非御金匠建议之维修方法，自行维修。<br>&nbsp;&nbsp;&nbsp;&nbsp;C）因不可抗力。
						</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（8）御金匠应就其自身造成的损害与损失按照法律规定承担相应责任。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（9）请您在领取商品并离开御金匠前仔细检查商品之维修状态，您对商品之领取将视为您已经确认商品已妥善维修。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（10）维修商品经维修完成并通知到客户之后，商品可保存3个月，超过3月后需收取保管费用(皮具类、腕表类10元/天，珠宝类、玉器类5元/天)</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（11）为处理您的维修要求，御金匠将取得您的个人信息，您同意将您的个人信息用于维修服务和相关顾客管理系统,维修商品的照片所有权由御金匠保留。</h5></td>
				</tr>
				<tr>
					<td><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">（12）您确认您已经完全理解并同意这些条款和条件。</h5></td>
				</tr>
			</table>
		</div>
		<hr id="writeHr" color="black" width="100%" style="height: 1px; margin: 0px; display: none">
		<div id="writeDiv" class="col-xs-4" style="width: 100%; display: none">
			<table id="customerTable" style="width: 100%">
				<tr>
					<td colspan="4"><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">我接受上述一般条款与条件和御金匠售后服务政策说明，并同意依据该条款和条件将产品存放予您处进行维修。</h5></td>
				</tr>
				<tr>
					<td colspan="2"><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">商品存放</h5></td>
					<td colspan="2"><h5 style="margin: 3px; font-family: 宋体; font-weight: normal;">商品退还</h5></td>
				</tr>
				<tr>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">经手人：</h5></td>
					<td>_________________________</td>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">日期：</h5></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">联系电话（必填）：</h5></td>
					<td>_________________________</td>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">经办人：</h5></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">顾客签字：</h5></td>
					<td>_________________________</td>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">确认无误，顾客签字：</h5></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h5 style="float: right; margin: 3px; font-family: 宋体; font-weight: normal;">证件及证件号（必填）：</h5></td>
					<td>_________________________</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>