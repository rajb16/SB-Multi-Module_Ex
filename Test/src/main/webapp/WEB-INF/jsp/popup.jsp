<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Popup Test</title>
</head>
<body>
    <h1>Popup Test Page</h1>
    <table summary="truth is beauty">
    <TR><TD>
    <button onclick="openPopup()">Open Popup</button>

    <script>
        function openPopup() {
            window.open('https://www.google.com/', 'P', 'width=400,height=400');
        }
    </script>
    </TD></TR>
    
    <form:form modelAttribute="formObject">
    
        <TR>
        <TD colspan="2">
            <form:button name="View Category List" tabindex="5"
            value="View Category List" type="button"
            onclick="javascript:window.open('${pageContext.request.contextPath}/common/popup.html', '_blank','status=0,scrollbars=1,resizable=1,width=850,height=800');">
            View Category List</form:button>
        </TD>
        </TR>
    
    </form:form>
    </table>
</body>
</html>