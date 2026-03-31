<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.embed ne 'true'}">
    <%@ include file="/WEB-INF/views/components/header.jspf" %>
</c:if>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Payment Form</h1>
        <c:if test="${param.embed ne 'true'}">
            <a href="<c:url value='/payments'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                Back To List
            </a>
        </c:if>
    </div>

    <div class="rounded-md border border-dashed border-stone-300 bg-stone-50 p-4 text-sm text-stone-700">
        <p class="font-medium">Implement the payment form UI in this file.</p>
        <ul class="mt-2 list-disc space-y-1 pl-5">
            <li>Create a <code>form</code> that POSTs to <code>/payments</code>.</li>
            <li>Add inputs named <code>paymentId</code> (hidden for edit), <code>paymentRef</code>, <code>poId</code>, <code>grnId</code>, <code>invoiceNumber</code>, <code>invoiceAmount</code>, <code>amountPaid</code>, <code>paymentDate</code>, <code>paymentMethod</code>, <code>transactionRef</code>, <code>status</code>, and <code>processedBy</code>.</li>
            <li>Add a submit button for create/update and keep the field names exactly as listed.</li>
            <li>Add a separate delete form in the list page that POSTs to <code>/payments/delete</code> with <code>paymentId</code>.</li>
        </ul>
    </div>
</section>

<c:if test="${param.embed ne 'true'}">
    <%@ include file="/WEB-INF/views/components/footer.jspf" %>
</c:if>
