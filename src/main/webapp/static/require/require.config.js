/**
 * Created by kee on 15/10/25.
 */
requirejs.config({

    baseUrl: ctx + '/static',


    //定义依赖关系
    shim: {
        'datatables.net': {deps: ['jquery', 'bootstrap', 'datatables'], exports: "datatables.net"},
        'metisMenu': ['jquery'],
        'slimscroll': ['jquery'],
        'bootstrap': ['jquery'],
        'jstree': ['jquery'],
        'ztree': ['jquery'],
        'validateZh': ['validate'],
        'additionalMethods': ['validate'],
        'validate': ['jquery'],
        'jsonFormat': ['jquery'],
        'layer': ['jquery'],
        'layui': ['jquery'],
        'element': ['layui'],
        'selector2': {deps: ['jquery', 'bootstrap'], exports: "selector2"},
        'webuploader':['jquery'],
        'icheck':['jquery'],
        'raty':['jquery'],
        'editor':['jquery'],
        'layuiTotal': ['jquery'],
        'dateTimePicker':['jquery'],
        'cityselect':['jquery','city.min'],
        'daterangepicker':{deps: ['jquery', 'bootstrap', 'moment'], exports: "daterangepicker"},
        'moment':['jquery'],
    } ,


    paths: {
        'jquery': 'jquery/jquery-2.2.4.min',
        'bootstrap': 'bootstrap/js/bootstrap.min',
        'validate': 'validate/jquery.validate.min',
        'validateZh': 'validate/localization/messages_zh.min',
        'additionalMethods': 'validate/additional-methods.min',
        'metisMenu': 'metisMenu/metisMenu.min',
        'slimscroll': 'slimscroll/jquery.slimscroll.min',
        'datatables.net': 'dataTables/js/dataTables.bootstrap.min',
        'datatables': 'dataTables/js/jquery.dataTables.min',
        'jstree': 'jstree/jstree.min',
        'ztree': 'ztree/js/jquery.ztree.all.min',
        'layer': 'layer/layer',
        'jsonFormat': 'jsonFormat/jsonFormat',
        'yaya': 'yaya/js/yaya',
        'jqueryJson':'jquery/jquery.json',
        'selector2':'select2/js/select2',
        'dateTimePicker':'dateTimePicker/js/jquery.datetimepicker',
        'webuploader':'webuploader/webuploader',
        'layui':'js/layui',
        'element':'js/element',
        'iconfont':'boundary/iconfont.js',
        'echarts':'echarts/echarts.min',
        'icheck':'customer/icheck',
        'raty':'raty/jquery.raty',
        'editor':'froala/js/froala_editor.min',
        'layuiTotal':'layui/layui' ,
        'city.min':'cityselect/js/city.min',
        'cityselect':'cityselect/js/jquery.cityselect',
        'weixin':'wx/jweixin-1.2.0',
        'daterangepicker':'daterangepicker/js/daterangepicker',
        'moment':'daterangepicker/js/moment',
    }
});