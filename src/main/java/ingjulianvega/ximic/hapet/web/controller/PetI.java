package ingjulianvega.ximic.hapet.web.controller;


import ingjulianvega.ximic.hapet.web.model.ApiError;
import ingjulianvega.ximic.hapet.web.model.PagedPetList;
import ingjulianvega.ximic.hapet.web.model.Pet;
import ingjulianvega.ximic.hapet.web.model.PetDto;
import ingjulianvega.ximic.hapet.web.model.PetList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface PetI {

    @Operation(summary = "Endpoint to get the list of pets", description = "Returns a list of pets", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The operation was successful.", content = @Content(schema = @Schema(implementation = PetList.class))),

            @ApiResponse(responseCode = "400", description = "400 - business error", content = @Content(schema = @Schema(implementation = ApiError.class))),

            @ApiResponse(responseCode = "500", description = "500 - server error", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<PagedPetList> get(@Parameter(in = ParameterIn.QUERY, description = "Using cache?", required = true, schema = @Schema()) @NotNull @Valid @RequestParam(value = "using-cache", required = true, defaultValue = "false") Boolean usingCache,
                                             @Parameter(in = ParameterIn.QUERY, description = "Page number", required = true, schema = @Schema()) @NotNull @Valid @RequestParam(value = "page-no", required = true, defaultValue = "0") Integer pageNo,
                                             @Parameter(in = ParameterIn.QUERY, description = "Page size", required = true, schema = @Schema()) @NotNull @Valid @RequestParam(value = "page-size", required = true, defaultValue = "3") Integer pageSize,
                                             @Parameter(in = ParameterIn.QUERY, description = "Sort by", required = true, schema = @Schema()) @NotNull @Valid @RequestParam(value = "sort-by", required = true, defaultValue = "name") String sortBy
    );


    @Operation(summary = "Endpoint to get the information of a pet given the id", description = "Returns a pet", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The operation was successful.", content = @Content(schema = @Schema(implementation = PetDto.class))),

            @ApiResponse(responseCode = "400", description = "400 - business error", content = @Content(schema = @Schema(implementation = ApiError.class))),

            @ApiResponse(responseCode = "500", description = "500 - server error", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<PetDto> getById(@Parameter(in = ParameterIn.PATH, description = "The pet id", required = true, schema = @Schema()) @NotNull @PathVariable("id") UUID id);

    @Operation(summary = "Endpoint to create a pet", description = "Creates a new pet", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The operation was successful."),

            @ApiResponse(responseCode = "400", description = "400 - business error", content = @Content(schema = @Schema(implementation = ApiError.class))),

            @ApiResponse(responseCode = "500", description = "500 - server error", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    @RequestMapping(value = "/",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> create(@Parameter(in = ParameterIn.DEFAULT, description = "Name of the new marital staus", required = true, schema = @Schema()) @NotNull @Valid @RequestBody Pet body);


    @Operation(summary = "Endpoint to update the information of a pet given the id", description = "Updates a pet", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The operation was successful."),

            @ApiResponse(responseCode = "400", description = "400 - business error", content = @Content(schema = @Schema(implementation = ApiError.class))),

            @ApiResponse(responseCode = "500", description = "500 - error de server, it'll show a generic user message", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateById(@Parameter(in = ParameterIn.PATH, description = "The pet id", required = true, schema = @Schema()) @NotNull @PathVariable("id") UUID id,
                                    @Parameter(in = ParameterIn.DEFAULT, description = "Name of the new marital staus", required = true, schema = @Schema()) @NotNull @Valid @RequestBody Pet body);


    @Operation(summary = "Endpoint to delete a pet", description = "Deletes a pet", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The operation was successful."),

            @ApiResponse(responseCode = "400", description = "400 - business error", content = @Content(schema = @Schema(implementation = ApiError.class))),

            @ApiResponse(responseCode = "500", description = "500 - error de server, it'll show a generic user message", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteById(@Parameter(in = ParameterIn.PATH, description = "The pet id", required = true, schema = @Schema()) @NotNull @PathVariable("id") UUID id);


}
