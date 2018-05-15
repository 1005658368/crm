/**
 * 显示费用池详细信息
 * @param costCode 费用池编号
 * @param product 产品编号
 * @param channel 渠道编号
 * @param startDate 开始日期
 * @param endDate 结束日期
 * @param clientId 经销商ID
 * @param postId 城市经理岗位ID
 */
function showCostMoneyDetail(costCode, product, channel, startDate, endDate, clientId, postId, empId) {
	var param = "&costCode=" + costCode
		+ "&product=" + product
		+ "&channel=" + channel
		+ "&startDate=" + startDate
		+ "&endDate=" + endDate
		+ "&clientId=" + clientId
		+ "&postId=" + postId
		+ "&empId=" + empId
		+ "&clickFunctionId=ceshi";
	var url = "costController.do?showCostMoneyDetail" + param;
	$.dialog({
			content: 'url:' + url,
			zIndex: 2100,
			title: '费用池详情',
			lock : true,
			parent:windowapi,
			width :1000,
			height :300,
			left :'65%',
			top :'55%',
			opacity : 0.4
	});
}