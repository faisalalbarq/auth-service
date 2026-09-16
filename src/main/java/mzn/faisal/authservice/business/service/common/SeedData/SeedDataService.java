package mzn.faisal.authservice.business.service.common.SeedData;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.common.SeedData.dto.UserIdentityTypeJsonItem;
import mzn.faisal.authservice.data.repository.UserIdentityTypeRepository;
import mzn.faisal.authservice.data.db.entity.UserIdentityType;
import mzn.faisal.authservice.utils.FileUtils;
import mzn.faisal.authservice.utils.JsonUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SeedDataService {

    private static final Logger logger = Logger.getLogger(SeedDataService.class.getName());

    private final UserIdentityTypeRepository userIdentityTypeRepository;


    public void Seed(){
        logger.info("Seeding data started...");

        seedEntity(
            userIdentityTypeRepository,
            "user_identity_type.json",
            new TypeReference<List<UserIdentityTypeJsonItem>>() {},
            item -> new UserIdentityType(null, item.getName())
        );
    }


    private <T , J> void seedEntity(
        JpaRepository<T, ?> repository,
        String fileName,
        TypeReference<List<J>> typeReference,
        Function<J , T> mapper
    ){
        if (repository.count() == 0L){
            logger.info("Seeding data for " + fileName + " started...");

            String resource = FileUtils.readResourceFile("seedDb/" + fileName);
            if (!resource.isBlank()){

                try {
                    List<J> items = JsonUtils.readValue(resource, typeReference);
                    List<T> entities = items.stream().map(mapper).toList();
                    repository.saveAll(entities);

                    logger.info("Seeding data for " + fileName + " completed successfully.");
                } catch (Exception e) {
                    logger.severe("Error occurred while seeding data for " + fileName + ": " + e.getMessage());
                }
            }
        }
    }
}
