<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Payments</h1>
        <a href="<c:url value='/payments/form'/>" class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">
            Go To Form
        </a>
    </div>

    <div class="overflow-x-auto">
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Reference</th>
                <th class="py-2">PO ID</th>
                <th class="py-2">Amount Paid</th>
                <th class="py-2">Status</th>
                <th class="py-2">Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${payments}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.paymentId}</td>
                    <td class="py-2">${row.paymentRef}</td>
                    <td class="py-2">${row.poId}</td>
                    <td class="py-2">${row.amountPaid}</td>
                    <td class="py-2">${row.status}</td>
                    <td class="py-2">${row.paymentDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty payments}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No payments yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
