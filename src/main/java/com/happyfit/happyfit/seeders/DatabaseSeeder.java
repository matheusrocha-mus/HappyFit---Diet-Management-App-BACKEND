package com.happyfit.happyfit.seeders;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.services.UserService;

import com.happyfit.happyfit.models.FoodOption;
import com.happyfit.happyfit.services.FoodOptionService;
import com.happyfit.happyfit.models.enums.FoodPortionEnum;

import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

@Component
public class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private UserService userService;

    private FoodOptionService foodOptionService;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
        UserService userService,
        FoodOptionService foodOptionService,
        JdbcTemplate jdbcTemplate
    ) {
        this.userService = userService;
        this.foodOptionService = foodOptionService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedFoodOptionsTable();
    }

    private void seedUsersTable() {
        String sql = "SELECT email FROM user_table U WHERE U.email = 'admin@example.com' LIMIT 1";

        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if (u == null || u.size() <= 0) {
            User user = new User(
                null,
                "Admin", 
                "1",
                "admin@example.com",
                "1234A:5678b",
                0,
                null,
                null,
                null,
                null,
                null
            );

            userService.create(user);
            logger.info("Admin Seeded");

        } else logger.trace("Admin Seeding Not Required");
    }

    private void seedFoodOptionsTable() {
        ArrayList<FoodOption> foodOptions = new ArrayList<FoodOption>();

        foodOptions.add(new FoodOption(null, "Ovo", 72f, 6.3f, 0.4f, 4.8f, FoodPortionEnum.UNIDADE, null, null));
        foodOptions.add(new FoodOption(null, "Banana", 105f, 1.3f, 27f, 0.4f, FoodPortionEnum.UNIDADE, null, null));
        foodOptions.add(new FoodOption(null, "Arroz", 130f, 2.7f, 28.2f, 0.3f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Feijão", 128f, 7.5f, 24.8f, 0.1f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Frango Grelhado", 144f, 28f, 0f, 3.6f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Costela de Porco", 256f, 26.81f, 0f, 15.71f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Lombo de Porco", 201f, 29.86f, 0f, 8.11f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Linguiça", 248f, 18.27f, 0f, 18.9f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Goiaba", 37f, 1.4f, 7.88f, 0.52f, FoodPortionEnum.UNIDADE, null, null));
        foodOptions.add(new FoodOption(null, "Aveia", 389f, 16.89f, 66.27f, 6.9f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Manteiga de Amendoim", 588f, 25.09f, 19.56f, 50.39f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Uvas Passas", 299f, 3.07f, 79.18f, 0.46f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Leite Integral", 60f, 3.22f, 4.52f, 3.25f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Leite Semi-Desnatado", 0f, 0f, 0f, 0f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Leite Desnatado", 0f, 0f, 0f, 0f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Leite de Soja", 54f, 3.27f, 6.28f, 1.75f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Requeijão", 260f, 8f, 3.3f, 24f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Batata", 110f, 3f, 26f, 0f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Suco de Uva", 55f, 0f, 13f, 0f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Iogurte (Maracujá)", 71f, 2.6f, 13f, 1f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Granola", 417f, 13f, 72.5f, 7.5f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Pão Francês", 137f, 4.4f, 25.95f, 1.5f, FoodPortionEnum.UNIDADE, null, null));
        foodOptions.add(new FoodOption(null, "Farofa", 429f, 0f, 71.43f, 11.43f, FoodPortionEnum.GRAMAS, null, null));
        foodOptions.add(new FoodOption(null, "Farinha de Trigo", 360f, 9.8f, 75.1f, 1.4f, FoodPortionEnum.GRAMAS, null, null));

        for (FoodOption foodOption : foodOptions) {
            String sql = "SELECT name FROM food_option_table F WHERE F.name = '" + foodOption.getName() + "' LIMIT 1";
            List<FoodOption> f = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

            if (f == null || f.size() <= 0) {
                foodOptionService.saveFoodOption(foodOption);
                logger.info(foodOption.getName() + " Seeded");

            } else logger.trace(foodOption.getName() + " Seeding Not Required");
        }
    }
}