package mzn.faisal.authservice.presentation.controller.base;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.common.TranslationService;
import mzn.faisal.authservice.business.dto.common.AppResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@RequiredArgsConstructor
public class ResponseTranslationAdvice implements ResponseBodyAdvice<Object> {

    private final TranslationService translationService;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {

        if (body instanceof AppResponse<?>(boolean success, Object data, String messageKey)) {

            String langHeader = request.getHeaders().getFirst("Accept-Language");
            String lang = (langHeader != null && !langHeader.isBlank()) ? langHeader : "ar";


            if (messageKey != null && !messageKey.isBlank()) {
                String translatedMessage = translationService.translate(messageKey, lang);

                return new AppResponse<>(
                        success,
                        data,
                        translatedMessage
                );
            }
        }

        return body;
    }
}