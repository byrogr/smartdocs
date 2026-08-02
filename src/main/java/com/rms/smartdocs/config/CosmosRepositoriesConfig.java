package com.rms.smartdocs.config;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCosmosRepositories(basePackages = "com.rms.smartdocs.repositories")
public class CosmosRepositoriesConfig {
}
