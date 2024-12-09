<!-- This file is included in the main layout file to display the navigation bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="container">
	<a class="navbar-brand" href="index.jsp">Employment Services</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item active"><a class="nav-link" href="index.jsp">Home
			</a>
			<%
			if (account != null ) { %>
				<li class="nav-item"><a class="nav-link" href="candidate/profile.jsp">Profile</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>	
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>	
			<%} else {%>
				<li class="nav-item"><a class="nav-link" href="auth/login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="auth/register">Register</a></li>
			<% } 
			%>		
		</ul>
	</div>
</div>
</nav>