/**
 * Created by kee on 9/22/16.
 */

define(['jquery', 'bootstrap', 'layer', 'metisMenu', 'validate'], function ($, bootstrap, layer) {

    /**
     * 弹出框初始化
     */
    layer.config({
        path: '/common/layer/'
    });

    /**
     * 菜单初始化,metisMenu
     */
    $('#side-menu').metisMenu();


    /**
     * 表单验证初始化
     * @param opts
     */
    var validate = function (opts) {
        $(opts.el).validate({
            //jquery validate 验证必须加入
            onkeyup: function (element) {
                $(element).valid()
            },
            rules: opts.rules,
            messages: opts.messages,
            errorElement: "div",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".form-group").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".form-group").addClass("has-success").removeClass("has-error");
            }
        })
    };

    return {
        layer: layer,
        validate: validate
    }

});
