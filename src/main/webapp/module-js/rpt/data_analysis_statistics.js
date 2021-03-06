require(['jquery','yaya','echarts','bootstrap','daterangepicker', 'datatables.net'], function ($, yaya,echarts,DrawEChart,daterangepicker) {
	
    var locale = {
		"format": 'YYYY/MM/DD',
		"separator": "-",
		"applyLabel": "确定",
		"cancelLabel": "取消",
		"fromLabel": "起始时间",
		"toLabel": "结束时间'",
		"customRangeLabel": "自定义",
		"weekLabel": "W",
		"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
		"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		"firstDay": 1
		};
    var myData = new Date();
    var endDate = myData.toLocaleDateString();
    myData.setDate(myData.getDate() - 6);
    var startDate = myData.toLocaleDateString();
    $('#dateArea').daterangepicker({
		"autoApply": true,
		"startDate": startDate,
		"endDate": endDate,
		"locale": locale
	});
    $('#dateArea2').daterangepicker({
		"autoApply": true,
		"startDate": startDate,
		"endDate": endDate,
		"locale": locale
	});

    $("#compare").click(function(){
    	var isChecked = $("#compare").prop("checked");
    	if(isChecked){
    		$("#date2").css("display", "block");
    	 } else {
     		$("#date2").css("display", "none");
    	 }
    });

    $("#search").click(function(){
    	var type = $("#type").val();
    	var dateArea = $("#dateArea").val();
    	var dateAreaStart = new Date($("#dateArea").val().split("-")[0]);
    	var dateAreaEnd = new Date($("#dateArea").val().split("-")[1]);
    	var dateAreaDayInterval = Math.floor((dateAreaEnd.getTime() - dateAreaStart.getTime())/86400000);
    	var dateAreaMonthInterval = dateAreaEnd.getMonth() - dateAreaStart.getMonth();
    	
    	if("Day" == type){
        	if($("#compare").prop("checked")){
            	var dateArea2Start = new Date($("#dateArea2").val().split("-")[0]);
            	var dateArea2End = new Date($("#dateArea2").val().split("-")[1]);
            	var dateArea2DayInterval = Math.floor((dateArea2End.getTime() - dateArea2Start.getTime())/86400000);
            	
            	if (dateAreaDayInterval == dateArea2DayInterval) {
        			getChartData();
            		$("#J_statisticsList").css("display", "");
            		setStatisticsList();
            	} else {
        			yaya.layer.msg("时间区间天数不一致！");
            	}
        	} else {
         		$("#J_statisticsList_wrapper").css("display", "none");
    			getChartData();
        	}
    		
    	} else if("Week" == type){
        	if($("#compare").prop("checked")){
            	var dateArea2Start = new Date($("#dateArea2").val().split("-")[0]);
            	var dateArea2End = new Date($("#dateArea2").val().split("-")[1]);
            	var dateArea2DayInterval = Math.floor((dateArea2End.getTime() - dateArea2Start.getTime())/86400000);
            	
            	if (dateAreaDayInterval == dateArea2DayInterval) {
        			if (dateAreaStart.getFullYear() == dateAreaEnd.getFullYear() || dateArea2Start.getFullYear() == dateArea2End.getFullYear()) {
        				if(dateAreaStart.getDay() != 1 || dateAreaEnd.getDay() != 0 || dateArea2Start.getDay() != 1 || dateArea2End.getDay() != 0){
    		    			yaya.layer.msg("开始日期必须为周一，结束日期必须为周日！");
        				} else {
        					getChartData();
        		    		$("#J_statisticsList").css("display", "");
        		    		setStatisticsList();
        				}
					} else {
		    			yaya.layer.msg("同一时间区间必须选择同一年的数据！");
					}
            	} else {
        			yaya.layer.msg("时间区间天数不一致！");
            	}
        	} else {
    			if (dateAreaStart.getFullYear() == dateAreaEnd.getFullYear()) {
    				if(dateAreaStart.getDay() != 1 || dateAreaEnd.getDay() != 0){
		    			yaya.layer.msg("开始日期必须为周一，结束日期必须为周日！");
    				} else {
    		     		$("#J_statisticsList_wrapper").css("display", "none");
    					getChartData();
    				}
				} else {
	    			yaya.layer.msg("同一时间区间必须选择同一年的数据！");
				}
        	}
    		
    	} else {
        	if($("#compare").prop("checked")){
            	var dateArea2Start = new Date($("#dateArea2").val().split("-")[0]);
            	var dateArea2End = new Date($("#dateArea2").val().split("-")[1]);
            	var dateArea2MonthInterval = dateArea2End.getMonth() - dateArea2Start.getMonth();

            	if (dateAreaMonthInterval == dateArea2MonthInterval) {
        			if (dateAreaStart.getFullYear() == dateAreaEnd.getFullYear() || dateArea2Start.getFullYear() == dateArea2End.getFullYear()) {
        				if(dateAreaStart.getDate() != 1 || dateArea2Start.getDate() != 1){
    		    			yaya.layer.msg("开始日期必须为1号！");
        				} else {
        					getChartData();
        		    		$("#J_statisticsList").css("display", "");
        		    		setStatisticsList();
        				}
					} else {
		    			yaya.layer.msg("同一时间区间必须选择同一年的数据！");
					}
            	} else {
        			yaya.layer.msg("时间区间选择月数不一致！");
            	}
        		
        	} else {
    			if (dateAreaStart.getFullYear() == dateAreaEnd.getFullYear()) {
    				if(dateAreaStart.getDate() != 1){
		    			yaya.layer.msg("开始日期必须为1号！");
    				} else {
    		     		$("#J_statisticsList_wrapper").css("display", "none");
    					getChartData();
    				}
				} else {
	    			yaya.layer.msg("同一时间区间必须选择同一年的数据！");
				}
        	}
    		
    	}
    });

   /*表格插件*/


    //创建ECharts图表方法
    $(function(){
        getChartData();//获取随机数据
    });

    function getChartData() {
        //获得图表的options对象
        var myChart = echarts.init(document.getElementById('box01'));

        var option = {
            title : {
                text: '数据分析',
                subtext: '数据统计'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['交易额']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'交易额',
                    type:'bar',
                    data:[],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };
        $.ajax({
            url:ctx+"/data-analysis-statistics/query",
            data:{
            	dateArea: $("#dateArea").val(),
            	dateArea2: $("#compare").prop("checked") ? $("#dateArea2").val() : null,
            	shopId: $("#shopId").val(),
            	type: $("#type").val(),
            	categoryId: $("#categoryId").val(),
            	deliverType: $("#deliverType").val(),
            	compareType: $("#compareType").val()
            },
            success:function (data) {
                option.xAxis[0].data = data.data.xAxis;
                option.series= data.data.series;
                myChart.hideLoading();
                myChart.setOption(option);
            }

        });
    };
    
    function setStatisticsList(){
        var $JStatisticsList = $('#J_statisticsList');
        
    	// 初始化
        var table = $JStatisticsList.DataTable({
            'scrollX': true,
            'processing': true,
            'searching': false,
            'lengthChange': false,
            'sort': false,
            'serverSide': true,
            'serverSide': true,
            "destroy": true,
            "paging": false,
            'lengthMenu': [10, 20, 50, 100],
            'ajax':{
                'url':ctx + '/data-analysis-statistics/queryList',
    			'method': 'get',
                'data': function (d) {
                	d.dateArea = $("#dateArea").val();
                	d.dateArea2 = $("#dateArea2").val();
                	d.shopId = $("#shopId").val();
                	d.type = $("#type").val();
                	d.categoryId = $("#categoryId").val();
                	d.deliverType = $("#deliverType").val();
                	d.compareType = $("#compareType").val();
                }
            },
        	'columns': [
        			{'data' : 'timeInterval'},
        			{'data' : 'firstValue'},
        			{'data' : 'secondValue'},
        			{'data' : 'variation'},
        			{'data' : 'changeRate'}
        			],
            'language': {
                'lengthMenu': '每页显示 _MENU_ 条记录',
                'zeroRecords': '没有检索到数据',
                'info': '第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条记录',
                'infoEmpty': '没有数据',
                'processing': '正在加载数据...',
                'paginate': {
                    'first': '首页',
                    'previous': '前页',
                    'next': '后页',
                    'last': '尾页'
                }
            }
        });
    };

});