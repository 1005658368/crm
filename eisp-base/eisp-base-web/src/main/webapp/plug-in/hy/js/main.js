
//验证两位小数
function floatReg(gets){
	//var num = typeof(obj.attr("datatype-float"))=="undefined"?2:Number(obj.attr("datatype-float"));
	var patt1=new RegExp(/^([1-9][\d]{0,9}|0)(\.[\d]{1,2})?$/);
	if (patt1.exec(gets) != null && patt1.exec(gets)[0] == gets)
		return true;
	else 
		return false;
}

//公共

//使用javascript通过className来获取元素
function getElementsbyClassName(n){
    
    var classElements=[],allElements=document.getElementsByTagName('*');
    
    for(var i=0;i<allElements.length;i++){
            if(allElements[i].className.indexOf(n)>=0){
                    classElements[classElements.length]=allElements[i];
            }
    }
    
    return classElements;
}

//弹出框
function tanchu(data){
	if(!data['close']){
		typeof(data['cont'])=='undefined'||data['cont']==''?data['cont']='数据加载中……':'';
		$.dialog({
			id: data['id'],
		    title: false,
		    content: data['cont'],
		    lock: true,
		    max: false,
		    min: false
		});
		
	}
	else{
		$.dialog({id: data['id']}).close();
		
	}

}

//分页函数
function page_func(page, size, totalpage, total, backfunc) {
	var str = "";

	str += '<table cellspacing="0" cellpadding="0" border="0"><tbody><tr>';
	str += '<td><select class="pagination-page-list" id="' + backfunc + '_page" onchange="' + backfunc + '(' + page + ',$(this).val())"><option value="15">15</option><option value="30">30</option><option value="45">45</option></select></td>';
	str += '<td><div class="pagination-btn-separator"></div></td>';
	str += '<td><a href="javascript:void(0)" class="l-btn l-btn-plain ' + (page > 1 ? '' : 'l-btn-disabled') + '" id=""><span class="l-btn-left" onclick="' + backfunc + '(1,$(\'#' + backfunc + '_page\').val())"><span class="l-btn-text"><span class="l-btn-empty pagination-first">&nbsp;</span></span></span></a></td>';
	str += '<td><a href="javascript:void(0)" class="l-btn l-btn-plain ' + (page > 1 ? '' : 'l-btn-disabled') + '" id=""><span class="l-btn-left" onclick="' + backfunc + '(' + (page - 1) + ',$(\'#' + backfunc + '_page\').val())"><span class="l-btn-text"><span class="l-btn-empty pagination-prev">&nbsp;</span></span></span></a></td>';
	str += '<td><div class="pagination-btn-separator"></div></td>';
	str += '<td><span style="padding-left:6px;"></span></td>';
	str += '<td><input type="text" size="' + totalpage + '" value="' + page + '" class="pagination-num"></td>';
	str += '<td><span style="padding-right:6px;">/' + totalpage + '</span></td>';
	str += '<td><div class="pagination-btn-separator"></div></td>';
	str += '<td><a href="javascript:void(0)" class="l-btn l-btn-plain ' + (page < totalpage ? '' : 'l-btn-disabled') + '" id=""><span class="l-btn-left" onclick="' + backfunc + '(' + (page - (-1)) + ',$(\'#' + backfunc + '_page\').val())"><span class="l-btn-text"><span class="l-btn-empty pagination-next">&nbsp;</span></span></span></a></td>';
	str += '<td><a href="javascript:void(0)" class="l-btn l-btn-plain ' + (page < totalpage ? '' : 'l-btn-disabled') + '" id=""><span class="l-btn-left" onclick="' + backfunc + '(' + totalpage + ',$(\'#' + backfunc + '_page\').val())"><span class="l-btn-text"><span class="l-btn-empty pagination-last">&nbsp;</span></span></span></a></td>';
	str += '<td><div class="pagination-btn-separator"></div></td>';
	str += '<td><a href="javascript:void(0)" class="l-btn l-btn-plain" id=""><span class="l-btn-left" onclick="' + backfunc + '(' + page + ',$(\'#' + backfunc + '_page\').val())"><span class="l-btn-text"><span class="l-btn-empty pagination-load">&nbsp;</span></span></span></a></td>';
	str += '</tr></tbody></table>';
	str += '<div class="pagination-info">' + (total > 1 ? ((size * (page - 1) - (-1)) + '-' + (Math.min(size * page, total))) : "") + ' 共' + total + '条</div><div style="clear:both;"></div>';

	return str;
}

//全选/全不选
function selall(obj,area,checked)
{
	var obj=$((typeof(area)=="undefined"?"":area)+" *[name='"+obj+"']");
	//var obj=document.getElementsByName(obj);
	var cnt=obj.length;
	for(i=0;i<cnt;i++)
	{
		obj[i].checked=typeof(checked)=="undefined"?true:checked;
		obj.eq(i).trigger('change');
	}
}

//全不选
function unselall(obj,area)
{
	var obj=$((typeof(area)=="undefined"?"":area)+" *[name='"+obj+"']");
	//var obj=document.getElementsByName(obj);
	var cnt=obj.length;
	for(i=0;i<cnt;i++)
	{
		obj[i].checked=false;
		obj.eq(i).trigger('change');
	}
}

//反选
function inselall(obj,area)
{
	var obj=$((typeof(area)=="undefined"?"":area)+" *[name='"+obj+"']");
	//var obj=document.getElementsByName(obj);
	var cnt=obj.length;
	for(i=0;i<cnt;i++)
	{
		if(obj[i].checked==true)
		{
			obj[i].checked=false;	
		}	
		else
		{
			obj[i].checked=true;	
		}
		obj.eq(i).trigger('change');
	}
}

//单选
function selone(obj,val,area)
{
	/*
	$("table").on('click',"*[name='id']",function(){
		var obj=document.getElementsByName("id");
		var cnt=obj.length;
		for(i=0;i<cnt;i++)
		{
				obj[i].checked=false;	
		}
	  $(this)[0].checked=true;
	})
	 */
	var obj=$((typeof(area)=="undefined"?"":area)+" *[name='"+obj+"']");
	//var obj=document.getElementsByName(obj);
	var cnt=obj.length;
	for(i=0;i<cnt;i++)
	{
		if(obj[i].value==val)
		{
			//obj[i].checked=false;	
		}	
		else
		{
			obj[i].checked=false;	
		}
	}
}

function ob(obj) {
	return document.getElementById(obj);
}

function getXmlhttp() {
	var http_request;
	if (window.XMLHttpRequest) {
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http_request) {
		window.alert("can't create XMLHttpRequest object.");
		return null;
	}
	return http_request;
}
function Aj_get(content_sour, content_des) {
	var ajax = getXmlhttp();
	content_sour = content_sour.split("?");
	obj_c = content_sour[0];
	obj_p = content_sour[1];
	ajax.open("GET", Gt_sour(obj_c, obj_p), false);
	ajax.send(null);
	if (content_des != "")
	{
		document.getElementById(content_des).innerHTML = ajax.responseText;
	} else
	{
		return ajax.responseText;
	}
}
function Aj_post(poststr, content_sour, content_des) {
	var ajax = getXmlhttp();
	var str = "";
	//content_sour=content_sour.split("?");
	obj_c = content_sour[0];
	obj_p = content_sour[1];
	//ajax.open("POST",Gt_sour(obj_c,obj_p),false);
	ajax.open("POST", content_sour, false);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.send(poststr);
	if (typeof(content_des)!="undefined"&&content_des != "")
	{
		document.getElementById(content_des).innerHTML = ajax.responseText;
		str = ajax.responseText;
	} else
	{
		str = ajax.responseText;
	}
	return str;
}


//获取radio的值
function getradiovalue(name,attr,area)
{
	return ck_rd_str(name,"radio","get_value",attr,area);
}

//获取checkbox的值
function getcheckboxvalue(name,attr,area)
{
	return ck_rd_str(name,"checkbox","get_value",attr,area);
}

//取值单选复选checkbox,radio
//----------checkbox,radio getvalue/getcount------------------------------------
function  ck_rd_str(frm_name,obj_type,back_type,attr,area)
{   
	  var obj=$((typeof(area)=="undefined"?"":area)+" *[name='"+frm_name+"']");
	  //var obj=document.getElementsByName(frm_name);
	  var str="";
	  var bk_str="";
	  var split_str="";
	  
	  if(obj_type=="checkbox")
	  {
		   split_str="||";
	  }
	  else if(obj_type=="radio")
	  {
		   split_str="";
	  }
	  
	  for(var i=0;i<obj.length;i++)  
	  { 
		  if(obj[i].checked)
		  {
			typeof(attr)=="undefined"||!attr?str+=split_str+obj[i].value:str+=split_str+obj[i].getAttribute(attr);
		  }
	  }

	  if(obj_type=="checkbox" && str!="")
	  {
		  str=str.substr("2");
	  }
	  
	  if(back_type=="get_value")
	  {
		   bk_str=str;
	  }
	  else if(back_type=="get_count")
	  {
		   bk_str=i;
	  }
	 return bk_str;		 
}  


