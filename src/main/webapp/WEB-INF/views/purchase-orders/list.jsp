<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Purchase Orders</h1>
        <a href="<c:url value='/purchase-orders/form'/>" class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">
            Go To Form
        </a>
    </div>

    <div class="overflow-x-auto">
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">PO Number</th>
                <th class="py-2">Supplier ID</th>
                <th class="py-2">Dept ID</th>
                <th class="py-2">Total</th>
                <th class="py-2">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${purchaseOrders}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.poId}</td>
                    <td class="py-2">${row.poNumber}</td>
                    <td class="py-2">${row.supplierId}</td>
                    <td class="py-2">${row.deptId}</td>
                    <td class="py-2">${row.totalAmount}</td>
                    <td class="py-2">${row.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty purchaseOrders}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No purchase orders yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
