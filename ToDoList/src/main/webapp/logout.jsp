<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- header include -->
<c:import url="/include/header.jsp">
<c:param name="title" value="Logout"></c:param>
</c:import>


<%
	session.invalidate();
	
	
%>
<c:redirect url="/welcome.jsp"></c:redirect>


<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>