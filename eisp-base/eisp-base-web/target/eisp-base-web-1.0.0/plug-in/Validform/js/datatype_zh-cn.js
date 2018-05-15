/**
 * datatype扩展
 */
$.Datatype.need1 = function(gets, obj, curform, regxp) {
    var need = 1, numselected = curform.find("input[name='" + obj.attr("name") + "']:checked").length;
    return numselected >= need ? true : "请至少选择" + need + "项！";
};
$.Datatype.need2 = function(gets, obj, curform, regxp) {
    var need = 2, numselected = curform.find("input[name='" + obj.attr("name") + "']:checked").length;
    return numselected >= need ? true : "请至少选择" + need + "项！";
};
$.Datatype.d = /^(\d*\.)?\d+$/;

// 添加 下拉框、combotree 验证
$.Datatype.select1 = function(gets, obj, curform, regxp) {
    var name = obj.attr("name") != undefined ? "name" : "comboname";
    var need = 1, numselected = curform.find("select[" + name + "='" + obj.attr(name)
            + "'] option[selected='selected']").length;
    return numselected >= need ? true : "请至少选择" + need + "项！";
};

$.Datatype.money1 = function(gets, obj, curform, regxp) {
    reg1 = /^([1-9]\d*)?$/;
    reg2 = /^(\d*\.\d{1,2})?$/;
    var value = curform.find("input[name='" + obj.attr("name") + "']").val().trim();
    return value.length!=0 && (reg1.test(value) || reg2.test(value)) ? true : "金额只能是整数或最多两位的小数";
};

$.Datatype.money2 = function(gets, obj, curform, regxp) {
    reg1 = /^(-?[1-9]\d*)?$/;
    reg2 = /^(-?\d*\.\d{1,2})?$/;
    var value = curform.find("input[name='" + obj.attr("name") + "']").val().trim();
    return value.length!=0 && (reg1.test(value) || reg2.test(value)) ? true : "金额只能是整数或最多两位的小数";
};