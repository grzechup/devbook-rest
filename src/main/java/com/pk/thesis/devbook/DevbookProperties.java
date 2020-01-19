package com.pk.thesis.devbook;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class DevbookProperties {

    @Value("${photo.store.path}")
    private String photoStorePath;

}


