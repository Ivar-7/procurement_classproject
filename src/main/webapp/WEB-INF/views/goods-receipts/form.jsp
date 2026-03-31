<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.embed ne 'true'}">
    <%@ include file="/WEB-INF/views/components/header.jspf" %>
</c:if>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Goods Receipt Form</h1>
        <c:if test="${param.embed ne 'true'}">
            <a href="<c:url value='/goods-receipts'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                Back To List
            </a>
        </c:if>
    </div>

    <div class="rounded-md border border-dashed border-stone-300 bg-stone-50 p-4 text-sm text-stone-700">
        <p class="font-medium">Implement the goods receipt form UI in this file.</p>
        <ul class="mt-2 list-disc space-y-1 pl-5">
            <li>Create a <code>form</code> that POSTs to <code>/goods-receipts</code>.</li>
            <li>Add inputs named <code>grnId</code> (hidden for edit), <code>grnNumber</code>, <code>poId</code>, <code>receiptDate</code>, <code>receivedBy</code>, <code>deliveryNoteRef</code>, <code>totalItems</code>, <code>status</code>, and <code>notes</code>.</li>
            <li>Add a submit button for create/update and keep the field names exactly as listed.</li>
            <li>Add a separate delete form in the list page that POSTs to <code>/goods-receipts/delete</code> with <code>grnId</code>.</li>
        </ul>
    </div>
</section>

<c:if test="${param.embed ne 'true'}">
    <%@ include file="/WEB-INF/views/components/footer.jspf" %>
</c:if>
