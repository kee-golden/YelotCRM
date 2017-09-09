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
					<img src="${ctx}/img/printLogo.png" style="height: 80px">
					<h4 style="float: right; line-height: 25px; margin-bottom: 5px;">
						御金匠皮具维修单登记表<br> 地址：上海市静安区铜仁路258号九安广场金座8D<br> 电话：4009611966&nbsp;&nbsp;&nbsp;021-62895588
					</h4>
				</div>
			</div>
			<div class="col-xs-4" style="line-height: 60px;">
				<h4 style="float: right; line-height: 25px; margin-bottom: 5px;">
					维修单号：${repairOrder.orderNo}<br> 开单日期：
					<fmt:formatDate value="${repairOrder.createAt}" pattern="yyyy-MM-dd" />
					<br> 经手人：${repairOrder.createUserName}
				</h4>
			</div>
		</div>
		<hr color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="customerDiv" class="col-xs-4" style="width: 100%">
			<h3 style="float: left; margin-bottom: 5px;">客户信息</h3>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td width="25%"><h4 style="float: right; margin-bottom: 5px;">客户姓名：</h4></td>
					<td width="25%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.customerName}</h4></td>
					<td width="25%"><h4 style="float: right; margin-bottom: 5px;">联系方式：</h4></td>
					<td width="25%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.customerPhone}</h4></td>
				</tr>
				<tr>
					<td width="25%"><h4 style="float: right; margin-bottom: 5px;">其他联系方式：</h4></td>
					<td width="25%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.customerPhoneSecond}</h4></td>
					<td width="25%"><h4 style="float: right; margin-bottom: 5px;">回寄地址：</h4></td>
					<td width="25%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.customerAddress}</h4></td>
				</tr>
			</table>
		</div>
		<hr color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="productDiv" class="col-xs-4" style="width: 100%">
			<h3 style="float: left; margin-bottom: 5px;">产品信息</h3>
			<br>
			<table id="productTable" style="width: 100%">
				<tr>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">分类：</h4></td>
					<td width="12%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.firstCategoryName}</h4></td>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">类型：</h4></td>
					<td width="12%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.secondCategoryName}</h4></td>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">品牌：</h4></td>
					<td width="12%"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.brandName}</h4></td>
					<td width="12%"></td>
					<td width="12%"></td>
				</tr>
				<c:forEach items="${repairOrder.productInfoList}" var="item">
					<c:if test="${item.id%4==1}">
						<tr>
					</c:if>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">${item.attributeName}：</h4></td>
					<td width="12%"><h4 style="float: left; margin-bottom: 5px;">${item.selectionValues}</h4></td>
					<c:if test="${item.id%4==0}">
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">外观描述：</h4></td>
					<td width="84%" colspan="7"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.imageDesc}</h4></td>
				</tr>
				<tr>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">产品附图：</h4></td>
					<td width="84%" colspan="7">
						<table style="width: 100%">
							<c:forEach items="${repairOrder.imagesList}" var="item" varStatus="status">
								<c:if test="${status.index%4==0}">
									<tr>
								</c:if>
								<td width="22%"><img src="${item}" width="100%" style="padding: 2px"></td>
								<c:if test="${status.index%4==3}">
									</tr>
								</c:if>
							</c:forEach>
							<c:if test="${repairOrder.imagesList.size()==1}">
								<td width="66%" colspan="3"></td>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()==2}">
								<td width="44%" colspan="2"></td>
							</c:if>
							<c:if test="${repairOrder.imagesList.size()==3}">
								<td width="22%"></td>
							</c:if>
						</table>
					</td>
				</tr>
				<tr>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">维修内容：</h4></td>
					<td width="84%" colspan="7"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.repairDesc}</h4></td>
				</tr>
				<tr>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">维修费用：</h4></td>
					<td width="36%" colspan="3"><h4 style="float: left; margin-bottom: 5px;">${repairOrder.totalPayment}</h4></td>
					<td width="12%"><h4 style="float: right; margin-bottom: 5px;">维修工期：</h4></td>
					<td width="36%" colspan="3"><h4 style="float: left; margin-bottom: 5px;">
							<fmt:formatDate value="${repairOrder.pickupAt}" pattern="yyyy-MM-dd" />
						</h4></td>
				</tr>
			</table>
		</div>
		<hr color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="remarkDiv" class="col-xs-4" style="width: 100%">
			<h3 style="float: left; margin-bottom: 5px;">备注信息</h3>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td><h4 style="margin-bottom: 5px;">（1）请妥善保管本单原件作为日后取件凭证</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（2）请您在签字前阅读本单据正后面之全部内容和条款</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（3）如有预计工期延迟会提前通知客户</h4></td>
				</tr>
				<tr>
					<td><h4 align="center" style="margin-bottom: 5px;">您的签字表示您已经仔细阅读，充分理解并且接受该等条款</h4></td>
				</tr>
			</table>
		</div>
		<hr color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="clauseDiv" class="col-xs-4" style="width: 100%">
			<h3 style="float: left; margin-bottom: 5px;">一般条款与条件</h3>
			<br>
			<table id="customerTable" style="width: 100%">
				<tr>
					<td><h4 style="margin-bottom: 5px;">（1）接收维修货品前必须由顾客签字确认，并附上一次性条码，御金匠对于顾客的维修货品不作任何鉴定。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（2）您特此授权御金匠在商品维修过程中采取任何必要且御金匠认为合适的方式执行维修。御金匠将相应安排维修，但商品是否可恢复其原厂出厂状态还取决于您使用的年限及商品的损伤程度。其中补色工艺后皮质的手感和颜色会略有差异，属于正常现象。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（3）如果经御金匠确认该商品无法维修，或者您未能在御金匠告知维修费用报价之日起（7日）内确认维修报价，御金匠会将未予维修的商品按起始状态退还与您。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（4）维修商品经维修完成且已支付全额维修费用（若有）后，御金匠会将商品归还给持有该维修单之人，该维修单的持有人被视为商品的合格所有者。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（5）本维修单原件为日后领取维修商品的唯一凭证，遗失不补，复印无效，请妥善保管。如果领取维修商品时，不能提供该维修单，为保障您的消费权益，御金匠保留要求核实身份证及暂存其复印件的权利。以证实顾客身份。代领维修件时，为保障您的消费权益，要求代领者必须持有本维修单原件并要求出示代领者本人身份证或其他有效证件（护照、机动车驾驶证等），御金匠有权暂存其复印件、登记身份信息和留下有效通讯（移动电话或座机）。并提供顾客本人预留的相关身份信息（如身份证号、护照号、机动车驾驶证号）以保障您的消费权益。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（6）如果收到领取维修商品通知后，顾客须按通知规定时间领取维修商品。顾客若逾期未领取维修商品，则御金匠有权收取相应的费用。若顾客逾期（三个月）未领取商品，顾客则自行承担商品的毁损灭失风险。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（7）如果因为以下情形造成任何损失，御金匠无需承担责任：<br>&nbsp;&nbsp;&nbsp;&nbsp;A）御金匠拒绝维修，而顾客强行要求维修，御金匠对此作出是否维修的决定。<br>&nbsp;&nbsp;&nbsp;&nbsp;B）顾客采取非御金匠建议之维修方法，自行维修。<br>&nbsp;&nbsp;&nbsp;&nbsp;C）因不可抗力。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（8）御金匠应就其自身造成的损害与损失按照法律规定承担相应责任。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（9）请您在领取商品并离开御金匠前仔细检查商品之维修状态，您对商品之领取将视为您已经确认商品已妥善维修。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（10）维修商品经维修完成并通知到客户之后，商品可保存3个月，超过3月后需收取保管费用(皮具类、腕表类10元/天，珠宝类、玉器类5元/天)</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（11）为处理您的维修要求，御金匠将取得您的个人信息，您同意将您的个人信息用于维修服务和相关顾客管理系统,维修商品的照片所有权由御金匠保留。</h4></td>
				</tr>
				<tr>
					<td><h4 style="margin-bottom: 5px;">（12）您确认您已经完全理解并同意这些条款和条件。</h4></td>
				</tr>
			</table>
		</div>
		<hr color="black" width="100%" style="height: 1px; margin: 0px">
		<div id="clauseDiv" class="col-xs-4" style="width: 100%">
			<table id="customerTable" style="width: 100%">
				<tr>
					<td colspan="4"><h4 style="margin-bottom: 5px;">我接受上述一般条款与条件和御金匠售后服务政策说明，并同意依据该条款和条件将产品存放予您处进行维修。</h4></td>
				</tr>
				<tr>
					<td colspan="2"><h4 style="margin-bottom: 5px;">商品存放</h4></td>
					<td colspan="2"><h4 style="margin-bottom: 5px;">商品退还</h4></td>
				</tr>
				<tr>
					<td><h4 style="float: right; margin-bottom: 5px;">经手人：</h4></td>
					<td>_________________________</td>
					<td><h4 style="float: right; margin-bottom: 5px;">日期：</h4></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h4 style="float: right; margin-bottom: 5px;">联系电话（必填）：</h4></td>
					<td>_________________________</td>
					<td><h4 style="float: right; margin-bottom: 5px;">经办人：</h4></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h4 style="float: right; margin-bottom: 5px;">顾客签字：</h4></td>
					<td>_________________________</td>
					<td><h4 style="float: right; margin-bottom: 5px;">确认无误，顾客签字：</h4></td>
					<td>_________________________</td>
				</tr>
				<tr>
					<td><h4 style="float: right; margin-bottom: 5px;">证件及证件号（必填）：</h4></td>
					<td>_________________________</td>
					<td><h4 style="float: right; margin-bottom: 5px;">确认无误，顾客签字：</h4></td>
					<td>_________________________</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>