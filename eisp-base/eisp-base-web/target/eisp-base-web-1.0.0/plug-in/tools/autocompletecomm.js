//------------------autocomplete-------------
 function parse(data){
  	var parsed = [];
     $.each(data.rows,function(index,row){
      	parsed.push({data:row,result:row,value:row.id});
     });
 	return parsed;
 }
 //------------------autocomplete-------------