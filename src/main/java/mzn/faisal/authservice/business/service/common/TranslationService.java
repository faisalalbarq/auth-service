package mzn.faisal.authservice.business.service.common;

import mzn.faisal.authservice.utils.JsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TranslationService {

    private final ApplicationContext applicationContext;
    private final Map<String, Map<String, String>> translations = new ConcurrentHashMap<>();

    public TranslationService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        loadTranslations();
    }

    private void loadTranslations() {
        try {
            Resource[] resources = applicationContext.getResources("classpath:i18n/*.json");
            for (Resource resource : resources) {
                String filename = resource.getFilename();
                if (filename != null && filename.endsWith(".json")) {
                    String lang = filename.substring(0, filename.indexOf("."));

                    try (InputStream inputStream = resource.getInputStream()) {
                        Map<String, String> langTranslations = JsonUtils.readValue(
                                inputStream,
                                new TypeReference<Map<String, String>>() {}
                        );
                        if (langTranslations != null) {
                            translations.put(lang.toLowerCase(), langTranslations);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public String translate(String key, String lang, Object... args) {
        if (key == null || key.isBlank()) return "";

        String targetLang = (lang != null && !lang.isBlank()) ? lang.toLowerCase() : "ar";

        Map<String, String> langTranslations = translations.getOrDefault(targetLang, translations.get("en"));
        if (langTranslations == null) {
            return key;
        }

        String template = langTranslations.getOrDefault(key, key);
        return (args.length > 0) ? MessageFormat.format(template, args) : template;
    }

    public void reload() {
        loadTranslations();
    }
}