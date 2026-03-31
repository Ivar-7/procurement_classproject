<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="mb-6 panel p-6">
    <h1 class="text-2xl font-semibold">Departmental Procurement Dashboard</h1>
    <p class="mt-2 text-sm text-stone-600">Use each module to capture records and move through the procurement lifecycle.</p>
</section>

<section class="grid gap-4 sm:grid-cols-2 lg:grid-cols-4">
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/departments'/>">
        <h2 class="font-semibold">Departments</h2>
        <p class="mt-1 text-sm text-stone-600">Master budget departments.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/suppliers'/>">
        <h2 class="font-semibold">Suppliers</h2>
        <p class="mt-1 text-sm text-stone-600">Vendor registration and status.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/items'/>">
        <h2 class="font-semibold">Items</h2>
        <p class="mt-1 text-sm text-stone-600">Catalogue and estimated prices.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/requisitions'/>">
        <h2 class="font-semibold">Requisitions</h2>
        <p class="mt-1 text-sm text-stone-600">Internal request tracking.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/quotations'/>">
        <h2 class="font-semibold">Quotations</h2>
        <p class="mt-1 text-sm text-stone-600">RFQ and vendor quotes.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/purchase-orders'/>">
        <h2 class="font-semibold">Purchase Orders</h2>
        <p class="mt-1 text-sm text-stone-600">Formal supplier orders.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/goods-receipts'/>">
        <h2 class="font-semibold">Goods Receipts</h2>
        <p class="mt-1 text-sm text-stone-600">Delivery and acceptance logging.</p>
    </a>
    <a class="panel p-4 hover:border-teal-600" href="<c:url value='/payments'/>">
        <h2 class="font-semibold">Payments</h2>
        <p class="mt-1 text-sm text-stone-600">Invoice and payment status.</p>
    </a>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
