<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<spring:url value="/webjars/jquery/1.9.1/jquery.min.js" var="jquery_url"/>
		<script>window.jQuery || document.write('<script src="${jquery_url}"><\/script>')</script>
		<script src="<spring:url value="/js/plugins.js"/>"></script>
		<script src="<spring:url value="/js/main.js"/>"></script>
		
		<script type="text/javascript" src="<spring:url value="/webjars/tinymce-jquery/3.4.9/jscripts/tiny_mce/jquery.tinymce.js"/>"></script>
		<script type="text/javascript">
			$(function() {
				$('textarea.tinymce').tinymce({
					// Location of TinyMCE script
					script_url : "<spring:url value="/webjars/tinymce-jquery/3.4.9/jscripts/tiny_mce/tiny_mce.js"/>",
					height: "500",
					// General options
					theme : "advanced",
					plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
					
					// Theme options
					theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
					theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
					theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
					theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
					theme_advanced_toolbar_location : "top",
					theme_advanced_toolbar_align : "left",
					theme_advanced_statusbar_location : "bottom",
					theme_advanced_resizing : true,
					
					// Example content CSS (should be your site CSS)
					//content_css : "css/fillmein.css",
					
					// Drop lists for link/image/media/template dialogs
					template_external_list_url : "lists/template_list.js",
					external_link_list_url : "lists/link_list.js",
					external_image_list_url : "lists/image_list.js",
					media_external_list_url : "lists/media_list.js",
					
					// Replace values for the template plugin
					template_replace_values : {
						username : "Some User",
						staffid : "991234"
					}
				});
			});
		</script>
	</body>
</html>