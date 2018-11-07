<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
 $(document).ready(function(){
     <c:forEach items="${noauto_operationCodes}" var="operation">
        
     	var id = '${operation.operationcode}';

     	$("#"+id).remove();
     </c:forEach>
 });
</script>