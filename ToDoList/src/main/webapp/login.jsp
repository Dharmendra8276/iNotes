<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
   .container{
     margin-top: 90px;
   }

   .mb{
     margin-bottom: 25px;
   }
   
   .mt{
   	 margin-top:25px;
   }
   
   .btn{
     width:100%;
   }
</style>



<!-- header include -->
<c:import url="/include/header.jsp">
<c:param name="title" value="Login"></c:param>
</c:import>

<%
	
	if(session.getAttribute("signup-success") != null){
		out.print("<div class='alert alert-success' role='alert'> Succesfully Signup! Please Login</div>");
	}
	
	if(session.getAttribute("warning") != null){
		out.println(session.getAttribute("warning"));
	}
%>
 

<div class="container">
   <div class="row">
   		<div class="col-md-4 offset-md-4">
   			<div class="card">
   				<div class="card-header">
   					<h2 class="text-center mt mb">LOGIN</h2>
				   <form action="loginServlet" method="post">
				     <div class="mb">
				       <input type="email" class="form-control" id="email" name="email" placeholder="Email Address" aria-describedby="emailHelp" required="required">
				     </div>
				     <div class="mb">
				       <!-- <label for="password" class="form-label">Password</label> -->
				       <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
				     </div>
				     
				     <button type="submit" class="btn btn-primary btn-block mt-3">Login</button>
				
				     <p class="mt-5 text-center " >Don't have an account? <a href="signup.jsp">SignUp</a></p>
				   </form>
   				</div>
   			</div>   		
   		</div>   
   </div>
</div>

<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>