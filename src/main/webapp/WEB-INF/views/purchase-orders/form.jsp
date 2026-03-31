<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Purchase Order Form</h1>
        <a href="<c:url value='/purchase-orders'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
            Back To List
        </a>
    </div>

    <div class="rounded-md border border-dashed border-stone-300 bg-stone-50 p-4 text-sm text-stone-700">
        <p class="font-medium">Do your form here.</p>
        <p class="mt-2">Implement your servlet here: PurchaseOrderServlet#doPost.</p>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
