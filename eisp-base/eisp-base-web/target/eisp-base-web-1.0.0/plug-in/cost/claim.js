/*******获取目录地址**********/
function getURLPrefixDir(){
	var prefix = $("#prefix").val();
	var dir = prefix.substring(0,prefix.length -9);
	return dir;
}