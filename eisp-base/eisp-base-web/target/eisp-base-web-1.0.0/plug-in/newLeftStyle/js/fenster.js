function yi_change(){
	var child_node = $('.topnav').children();
	for(var i = 0;i<icon_white.length;i++){
		for (var j=0;j<child_node.length;j++) {
				if(child_node[j].firstChild.firstChild.getAttribute('src')==icon_white[i]){
					child_node[j].firstChild.firstChild.setAttribute('src',icon_black[i]);
			}
			
		}
		
	}
}

//------------------------内容左侧菜单部分开始-----------------------------
//关闭子菜单闭合符号
function base(xuan){
	//菜单功能关闭时下面的子菜单全部闭合
	$(xuan).next().slideUp('300');
	//取消选中状态,去除背景色，和字体颜色
	$(xuan).css('background-color','').css('color','#777');
	//把所以右边的三角符号闭合
	$(xuan).children('span').children('img').attr('src','plug-in/newLeftStyle/img/off.png');
}
//点击其他一级菜单或者二级菜单时，已经点击的一级菜单图标换色
function yi_change(){
	var child_node = $('.topnav').children();
	for(var i = 0;i<icon_white.length;i++){
		for (var j=0;j<child_node.length;j++) {
				if(child_node[j].firstChild.firstChild.getAttribute('src')==icon_white[i]){
					child_node[j].firstChild.firstChild.setAttribute('src',icon_black[i]);
			}
			
		}
		
	}
}
//当自己关闭自己的子菜单时，还是给白色三角
function white_sanjiao(that){
	if(that.children('span').children('img').attr('src')=='plug-in/newLeftStyle/img/off.png'){
		that.children('span').children('img').attr('src','plug-in/newLeftStyle/img/off_white.png');
	}
}
//功能菜单效果
//点击隐藏
$(".fenster_content_left_h4 img").click(function(){
	$('.fenster_content_left').css('position','absolute');
	$('.fenster_content_left1').css('display','block');
	$('.fenster_content_left').animate({left: '-16%'},400);
	$('.fenster_content_right').css('width','98%').css('*width','97.9%');
	base('ul.topnav li a');
});
//点击显示
$('.fenster_content_left1').on('click',function(){
	$('.fenster_content_left').animate({left:'0px',},400);
	setTimeout(function(){
		$('.fenster_content_right').css('width','84%');
		$('.fenster_content_left').css('position','static');
		$('.fenster_content_left1').css('display','none');
	},400);

});

//存储白色黑黑色图片路径数组
var icon_black=["plug-in/newLeftStyle/img/shezhi.png","plug-in/newLeftStyle/img/renpinke.png","plug-in/newLeftStyle/img/yusuan.png","plug-in/newLeftStyle/img/feiyongzhixing.png","plug-in/newLeftStyle/img/shujubaobiao.png"];
var icon_white=["plug-in/newLeftStyle/img/shezhi_white.png","plug-in/newLeftStyle/img/renpinke_white.png","plug-in/newLeftStyle/img/yusuan_white.png",
				"plug-in/newLeftStyle/img/feiyongzhixing_white.png","plug-in/newLeftStyle/img/shujubaobiao_white.png"];
//点击一个一级菜单时，之前点击的一级菜单闭合
$('ul.topnav>li>a').on('click',function(){
	yi_change();
//	点击一级菜单时更换图片
	var img_src=$(this).children('img').attr('src');
	for(var i=0;i<icon_black.length;i++){
		if(img_src==icon_black[i]||img_src==icon_white[i]){
			$(this).children('img').attr('src',icon_white[i]);
			break;
		}
	}
	//点击其他一级菜单时，已经打开的菜单闭合
	$('ul.topnav>li>a').next().slideUp('300');
	//刚点击的菜单展开，
	$(this).slideDown('300');
	base('ul.topnav li a');
	white_sanjiao($(this));
	
});


//2级菜单闭合
$('ul.topnav>li>ul>li>a').on('click',function(){
	yi_change();
	//点击其他2级菜单时，已经打开的菜单闭合
	$('ul.topnav>li>ul>li>a').next().slideUp('300');
	//刚点击的菜单展开，
	$(this).slideDown('300');
	base('ul.topnav>li>ul li a');
	white_sanjiao($(this));
});
//3级菜单
$('ul.topnav>li>ul>li>ul>li>a').on('click',function(){
	//点击其他3级菜单时，已经打开的菜单闭合
	$('ul.topnav>li>ul>li>ul>li>a').next().slideUp('300');
	//刚点击的菜单展开，
	$(this).slideDown('300');
	base('ul.topnav>li>ul>li>ul li a');
	white_sanjiao($(this));
});
//4级菜单
$('ul.topnav>li>ul>li>ul>li>ul>li>a').on('click',function(){
	//点击其他3级菜单时，已经打开的菜单闭合
	$('ul.topnav>li>ul>li>ul>li>ul>li>a').next().slideUp('300');
	//刚点击的菜单展开，
	$(this).slideDown('300');
	base('ul.topnav>li>ul>li>ul>li>ul li a');
	white_sanjiao($(this));
});

//选中时给背景色
$('.topnav li>a').on('click',function(){
//	选中子菜单时，父级菜单的三角变成黑色
	$('.topnav li>a>span>img[src="plug-in/newLeftStyle/img/open_white.png"]').attr('src','plug-in/newLeftStyle/img/open.png');
	//先清空该选择器选中标签的所有背景色
	$('.topnav  li>a').css({'background-color': '', "font-weight": "normal", 'color': '#000'});
	//在给点击的标签赋背景色
	$(this).css({"color": "#1c8fbd","font-weight": "bold"});
});

//多级可展开菜单
$(document).ready(function() {
	//debugger;
	$(".topnav").accordion({
		accordion:false,
		speed: 500,
		closedSign: '<img src="plug-in/newLeftStyle/img/off.png" draggable="false">',
		openedSign: '<img src="plug-in/newLeftStyle/img/open_white.png" draggable="false">'
	});
});
(function($){
	//debugger;
    $.fn.extend({
    accordion: function(options){
    	//debugger;
		var defaults = {
			accordion: 'true',
			speed: 300,
			closedSign: '<img src="plug-in/newLeftStyle/img/off.png" draggable="false">',
			openedSign: '<img src="plug-in/newLeftStyle/img/open.png" draggable="false">'
		};
		var opts = $.extend(defaults, options);
 		var $this = $(this);
 		$this.find("li").each(function() {
 			if($(this).find("ul").size() != 0){
   				$(this).find("a:first").append("<span>"+ opts.closedSign +"</span>");
 				if($(this).find("a:first").attr('href') == "#"){
 		  			$(this).find("a:first").click(function(){return false;});
 		  		}
 			}
 		});
 		$this.find("li.active").each(function() {
 			$(this).parents("ul").slideDown(opts.speed);
 			$(this).parents("ul").parent("li").find("span:first").html(opts.openedSign);
 		});
  		$this.find("li a").click(function() {
  			if($(this).parent().find("ul").size() != 0){
  				if(opts.accordion){
  					if(!$(this).parent().find("ul").is(':visible')){
  						parents = $(this).parent().parents("ul");
  						visible = $this.find("ul:visible");
  						visible.each(function(visibleIndex){
  							var close = true;
  							parents.each(function(parentIndex){
  								if(parents[parentIndex] == visible[visibleIndex]){
  									close = false;
  									return false;
  								}
  							});
  							if(close){
  								if($(this).parent().find("ul") != visible[visibleIndex]){
  									$(visible[visibleIndex]).slideUp(opts.speed, function(){
										$(this).parent("li").find("span:first").html(opts.closedSign);
  									});
  									
  								}
  							}
  						});
  					}
  				}
  				if($(this).parent().find("ul:first").is(":visible")){
  					$(this).parent().find("ul:first").slideUp(opts.speed, function(){
//						$(this).parent("li").find("span:first").delay(opts.speed).html(opts.closedSign);
  					});
  				}else{
  					$(this).parent().find("ul:first").slideDown(opts.speed, function(){
  						$(this).parent("li").find("span:first").delay(opts.speed).html(opts.openedSign);
  					});
  				}
  			}
  		});
    }
});
})(jQuery);

//------------------------内容左侧菜单部分结束-----------------------------