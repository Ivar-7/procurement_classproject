<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-8 text-center">
    <h1 class="text-3xl font-bold text-stone-800">500</h1>
    <p class="mt-2 text-stone-600">Something went wrong on the server.</p>
    <a href="<c:url value='/dashboard'/>" class="mt-4 inline-block rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Go to dashboard</a>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
