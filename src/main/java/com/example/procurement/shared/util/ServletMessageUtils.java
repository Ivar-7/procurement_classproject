package com.example.procurement.shared.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public final class ServletMessageUtils {

    private ServletMessageUtils() {
    }

    public static void redirectWithMessage(
        HttpServletRequest request,
        HttpServletResponse response,
        String path,
        String key,
        String message
    ) throws IOException {
        String safeMessage = message == null ? "" : message;
        response.sendRedirect(request.getContextPath() + path + "?" + key + "="
            + URLEncoder.encode(safeMessage, StandardCharsets.UTF_8));
    }
}
