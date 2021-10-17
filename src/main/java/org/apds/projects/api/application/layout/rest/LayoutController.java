package org.apds.projects.api.application.layout.rest;

import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apds.projects.api.application.auth.service.UserService;
import org.apds.projects.api.application.layout.entity.LayoutPath;
import org.apds.projects.api.application.layout.service.LayoutService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(path = "/api/layout", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class LayoutController {

    private final UserService userService;

    private final LayoutService layoutService;

    @GetMapping("/items")
    @PreAuthorize("hasAuthority('admin')")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer ...")
    public Flux<LayoutPath> itemPath(@ApiIgnore @AuthenticationPrincipal UserDetails userInfo) {
        log.info("##### user {}", userInfo);
        return layoutService.findItemsPath();
    }
}
