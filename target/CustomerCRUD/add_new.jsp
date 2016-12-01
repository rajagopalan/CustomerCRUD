<%-- 
    Document   : add_new
    Created on : Feb 17, 2016, 11:08:25 AM
    Author     : 32588
--%>

<!DOCTYPE html>
<html>
<head>
<title>CustomerAdd</title>
 <link rel="shortcut icon" href="favicon.ico" />

<script src="validation.js">

</script>

<style>


#footer {
	clear: both;
	position: relative;
	z-index: 10;
	height: 3em;
	margin-top: -3em;
}

 #header {
	position: relative;
	height: 7em;
	margin-top: -1em;
	margin-left: -1em;
	border-bottom: 1px solid black;
} 

.headerclass{
	margin-left: 4em;
}


hr {
	border: 0;
	height: 0;
	border-top: 1px solid rgba(0, 0, 0, 0.1);
	border-bottom: 1px solid rgba(255, 255, 255, 0.3);
}
 
 form {
	font-size: 14px;
	font-family: Helvetica, Arial, sans-serif;
	color: rgb(51, 51, 51);
}

body {
	font-size: 14px;
	font-family: Helvetica, Arial, sans-serif;
	color: rgb(51, 51, 51);
}


 </style>
</head>
<body>

	       <%@ page import="devops.*"%>
	       <%@ page import="java.util.Properties"%>
	       <%@ page import="java.io.*"%>
	       
	       <%
	
    AuthUsers user = (AuthUsers) session.getAttribute("user");  
    //session.setAttribute("user",user);
    
     if (user ==  null)
             {
            	 response.sendRedirect("login.jsp");
             }
    
     Properties prop = new Properties();
     InputStream input = null;
		String filename = "database_config.properties";
		input = getClass().getClassLoader().getResourceAsStream(
				filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
			//pw.println("file not found");
			return;
		}

		//load a properties file from class path, inside static method
		prop.load(input);
		String productVersion = prop.getProperty("product_version");
    
           %>

	<div id="header" style="background-color: #add8e6;">
		<TABLE class="headerclass">
			<tr>
				<TD><img src="hexaware_logo.png" alt="logo" /></TD>
				<TD><h1>DevOps Solutions</h1></TD>
				
				<TD>
				<TABLE class="productVersionclass">
				<tr>
				<TD>Product Version: <%= productVersion %></TD>
				</tr>
				</TABLE>
				</TD>
				
			</tr>
		</TABLE>
	</div>
	<br/>
	<div  style="margin-left: 5em;">
	<h3>Add Customer</h3>
	<hr/>
	<FORM NAME="Add_form" METHOD="POST" ACTION="add" >
		<TABLE   cellspacing="1">
			<TR>
				<TD><h5>Name</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="name" SIZE="25" required>
				</TD>
			</TR>
			<TR>
				<TD><h5>Address</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="address" SIZE="25" required>
				</TD>
			</TR>
			<TR>
				<TD><h5>Contact Number</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="contactNumber" SIZE="25" id="PhNo" required>
				</TD>
			</TR>
			<TR>
				<TD><h5>AlternateContactNumber</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="alternateContactNumber" SIZE="25"
					required></TD>
			</TR>
			<TR>
				<TD><h5>Specialty</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="specialty" SIZE="25" required></TD>
			</TR>
			<TR>
				<TD><h5>QualificationSummary</h5></TD>
				<TD><INPUT TYPE="TEXT" NAME="qualificationSummary" SIZE="25"
					required></TD>
			</TR>
			<TR>
				<TD></TD>
				<TD><BUTTON TYPE="SUBMIT" VALUE="Create" NAME="B1" onclick="return validatePhoneNo();">Submit</BUTTON>
				<BUTTON TYPE="RESET" VALUE="Reset" NAME="B2">Reset</BUTTON></TD>
			</TR>

		</TABLE>
	</FORM>
	<br/>
	<div class="msg_position" style="color: #FF0000;">${add_fail_message}</div>
	<div class="msg_position" style="color: #009933;">${add_success_message}</div>
	<a href="GetData"> Back to Customer List</a><br/><br/>
	<br/>
	<hr>
	<br/>
	<footer>&#169; Hexaware Technologies Limited. All rights reserved 2016 </footer>
	</div>
</body>
</html>
