
import com.example.shortapitest.eLearningApi.entity.eLearning.ELearningMenu;
import com.example.shortapitest.eLearningApi.entity.image.BaseImage;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuImage extends BaseImage {

    @ManyToOne(fetch = FetchType.LAZY)
    private ELearningMenu eLearningMenu;    //메뉴에 대한 이미지 설정

}
