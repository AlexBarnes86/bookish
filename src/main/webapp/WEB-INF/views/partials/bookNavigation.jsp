<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.toastedbits.bookish.domain.Category" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%--
	Friggin' JSP doesnt allow parameters to be passed directly to a page included through <jsp:include>
	Can't use <c:set var="categoryTree" value="${categoryTree.child}" scope="request"> either because we need to be able to pop back up the tree,
	would need to use a stack to recursively output the tree in this manner
	After a bit of deliberation, this printTree monstrosity came to mind: 
--%>

<%!
	public void printTree(JspWriter out, Category category, HttpServletRequest request) throws IOException {
		out.print("<ul>");
			out.print("<li>");
				out.print("<a href=\"" + request.getContextPath() + "/books?category=" + category.getId() + "\">");
					out.print(category.getName());
				out.print("</a>");
				printAdminPanel(out, category, request);
				if(category.getChildren() != null && category.getChildren().size() > 0) {
					for(Category child : category.getChildren()) {
						printTree(out, child, request);
					}
				}
			out.print("</li>");
		out.print("</ul>");
	}

	public void printAdminPanel(JspWriter out, Category category, HttpServletRequest request) throws IOException {
		out.print("<div class=\"adminPanel\">");
			out.print("<form action=\"" + request.getContextPath() + "/category\" method=\"post\">");
				out.print("<input type=\"hidden\" name=\"parent\" value=\"" + category.getId() + "\">");
				out.print("<input type=\"text\" name=\"name\"><input type=\"submit\" value=\"+\"/>");
			out.print("</form>");
			out.print("<form action=\"" + request.getContextPath() + "/category/" + category.getId() + "\" method=\"post\">");
				out.print("<input type=\"hidden\" name=\"_method\" value=\"delete\"/>");
				out.print("<input type=\"submit\" value=\"-\">");
			out.print("</form>");
		out.print("</div>");
	}
%>

<div id="bookCategoriesPane" class="contentPaneLeft">
<%--	<c:choose>
		<c:when test="${categoryTree != null && categoryTree.parent != null}">
			<h3>${categoryTree.name}</h3>
			<a href="<spring:url value="/books"/>">Home</a>
			<a href="<spring:url value="/books?category=${categoryTree.parent.id}"/>">Up</a>
		</c:when>
		<c:otherwise>
			<h3>Home</h3>
		</c:otherwise>
	</c:choose>
	
	<div class="adminPanel">
		<form action="<spring:url value="/category?parent=${categoryTree.id}"/>" method="post">
			<input type="text" name="name"><input type="submit" value="+">
		</form>
	</div>
	
	<c:choose>
		<c:when test="${categoryTree != null && categoryTree.children != null && categoryTree.getChildren().size() > 0}">
			<%
				Category categoryTree = (Category)request.getAttribute("categoryTree");
				for(Category category : categoryTree.getChildren()) {
					printTree(out, category, request);
				}
			%>
		</c:when>
	</c:choose>
	<div style="clear:both">&nbsp;</div> --%>
</div>