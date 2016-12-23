package io.davis.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by rai on 16/12/23.
 */
@ApiModel
@Data
public class UserDraft {
  @NotNull(message = "name can not be null")
  private String name;

  @NotNull
  @Min(value = 0, message = "age can not be less than 0")
  private Integer age;
}
