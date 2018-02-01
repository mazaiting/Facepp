package com.mazaiting.facepp.funcation.face.detect.service;

import com.mazaiting.facepp.funcation.face.detect.bean.DetectBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author mazaiting
 * @date 2018/1/30
 */

public interface DetectApi {

/**
 * 网络图片
    --> POST https://api-cn.faceplusplus.com/facepp/v3/detect
    Content-Type: application/x-www-form-urlencoded
    Content-Length: 170

    api_key=rv5Pab4EsmqWFft6tBKK5GqvsBGerHeb&api_secret=OVNhOVgsxOhq4wsYzSj7-88ZD1BO3vHP&image_url=http%3A%2F%2Fpic1.hebei.com.cn%2F003%2F005%2F869%2F00300586905_449eedbb.jpg
    --> END POST (170-byte body)
 */
    @FormUrlEncoded
    @POST("facepp/v3/detect")
    Observable<DetectBean> getDetectUrl(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("image_url") String image_file,
            // 可选 1- 检测。返回 83 个人脸关键点; 2- 检测。返回 106 个人脸关键点。
            @Field("return_landmark") int return_landmark,
            // 可选
            @Field("return_attributes") String return_attributes
            );

/**
 * 本地图片
    --> POST https://api-cn.faceplusplus.com/facepp/v3/detect
    Content-Type: application/x-www-form-urlencoded
    Content-Length: 482644

    api_key=rv5Pab4EsmqWFft6tBKK5GqvsBGerHeb&api_secret=OVNhOVgsxOhq4wsYzSj7-88ZD1BO3vHP&image_base64=iVBORw0KGgoAAAANSUhEUgAABD0AAAI2CAIAAADcgWTWAAAAA3NCSVQICAjb4U%2FgAAAgAElEQVR4%0AnOy9WZNkx3Em6u4RcZbMrOrqBWgCkDDkyEBqs6Gkh5GeZDS7dq%2BZTD9y7rOeZMO5MhuREk2iRHEI%0ANsClSRALARBEd6O36qrKyjxLhLvfBz8n8lR1NxZia0Dns7a2zKzMs0eEf%2B6fu2OMEWbMmPHlgqoi%0AIhFN3wKAiHyux%2FVxofjoz%2B3sPj6Q9SN9%2F3HH8wTCex9j9N4jYtu2iFgUBTN%2F3sf1RENAnXMAYBeK%0AiFSVmb2nR39fhkdx8kAKAKSUyqIEAFEhJFGxlbcaRyggTp8lJFJQVc2bElVVTSrOOQ%2BkoMACAIQE%0AiGDj2raGIMyKQERJejtsAEBAAFBQABARNMCZJ1h02Gb%2B3L6PgAqaD8mOCmE4yfxhPlq7XPlS5Ksh%0A7JjZOYeI9jQ650RE5MyUlU9c4KONx08K0zOaggCdcyklEQkhiAgze%2B%2FzvJpPVkTyrDvjcXDet11b%0AlZWobLfb1XKVB8hH2k5%2BYPIFt0%2BSECIQAQKADMsfIhJqBqIiIiEiop7d4LnX04efiBBRmVRVle1P%0A9iESxb4nInLnj82%2BOY48VFARERFyzr6mqkrjnwFVePLb8Qt5Cw8doQgQkU1T9gTmt7YdnQAASM%2FM%0AV3mz05kn71RVe05VWSEAM4uIrSbMrJPn3yEBDCeGoXjk%2FSIimwBDCDYPxBhtPcrH9vDdzPAf%2BEDM%0AmDHjC4eHZzqb7Lz%2FYg%2F5x%2FGElNInsv3CfbTr80XhLV3XmUUoIraS5cdjxvvg3HJu%2F2c74GGYqXJu%0AuVUdyE%2FixMz2c%2Becd15jP%2BwIdhY6IopKHrN5U%2FZWRJIKADijFmYfew8iZrZAtq5g57mACQPZfaKa%0A39mhOnQDsQF5mI2cM%2BYU1AzCx10NO%2BDpidh1mP4vIn3fh1BOzf2d4eIezQ8%2FL2TS9TiLKmMeXx8G%0AXd8552KKwYfVctV2rarWVf07b%2FDcNVdVABRRRKRxICMCnXmeZccBJswEHjLfz7FQVUWyAXV21Zhu%0AZNyQWeT27Az7xeE7NiEgIiEpaFKZuBWGHeUjISJzImSqTET2w4FIDNvdsRSjRjDSKtvCcISTA55a%0AC%2Bcu5njdsPRlPkvbvm08hLA7fRajMc65x7HPtm2rqrLXIYS%2B74uiOD09resa4AyVeiRwjrfMmPHl%0AQ552z%2BHzPq6Pi8fxBLOBPj4kfTnjDyLinNstkEQAQERf9Pjbpw2LWuhodmTup%2Fro5%2BTsWkt53FXl%0Awgx051xZBhHYbreqWvklAExjHrYBRYtuAAAoAea3FllRQARvv2IQ0RCQGVSBCBRABBTB%2B4GrPNKF%0AuTvHybTgPdmOzgUQsqP63K%2FOme%2FvH15Q1ZRijNF7KooCALz3RBAjA8rUDAIAIjOMPh%2FHwOOmSo6p%0ALEs7TueceZ2Lonjc5f0STLmfKpz3ABBT3G63i8XCpqbNZmOPx4eHnuUbGYIBERUYAFAh0%2FjUt5Mv%0ACwCAKACgO%2BOSOLfZh%2F0ROARHzsclqqpiZuaoY2xkcHmAy084EGZqAYQ5MBKZM29x%2BQvjYWSXk20H%0AEY1IT8f4cO4iU7bzyEdR5YzfIb%2BY%2Bjen5x5FU0qq7JwLtFtzOfXOOSJQVWEGAOeccy6mR68v3vu%2B%0A77uuWy6XIYR79%2B499dRT%2Bcq8%2FxwFM2%2BZMeNLiWlE2D6xF33ff67H9XHxODPmkzqv8jFx7S86TNxS%0AFIUtZubrmo2qD4SMqqoYo4WqdBBgPJq36Blp0E7y1LXRTBmTQ4QQjAVxRzBGWs6wApxIOt1O7QmI%0AqormZFVgZpQhjppSUtUQAiDGGFnFOYfEj7SrYGJ7PfJEcAIAiDFO3%2BZNuYm%2BBSYmztRdPf2%2Fqsq2%0AbZ3DEIIZ%2FSLSdV1ZhazHsytjnun0GLvn08bjLkvq45S32D2qqspOeZAJTdzzs1%2Fg%2FdF07Xq9vnr1%0Aat%2F3TdOISFmWn8hFs7vQMxpvSSmB7MTSdRkmd0pU1YSY5%2BJ7%2BTEYlJaTh39gBbJ74O3u21tTEtos%0AkZm%2FiJShsoGDiECYN0Xe5c8z0QGbQcY9TgfvOcs%2B773ruuk4za9zGHPKoxAxRYGzg91%2BaELifEb5%0AfIt6EWMkgqIoSKHve4tAusHLwKqqY2yHiPgxd9J4i6oWRdE0zWq1ijGu1%2BsrV64YEzt3%2FWfeMmPG%0Alx95qoJxBoHzRtWXClM9zMcBf0RdNX5BLP%2BU0uHh4WKxsEdib29vmoEw43EQYABIKXVdl1LCMXmD%0A6NF6QuY4MRpM3u4BoOv67Xb74o%2Buffvb337ppZ8cHh4yq3PO46X8W6Uz0QyzULIxYR8wASIWPhCR%0A9LHrOo9UFEVVlH3fA2FZlkrYtm1MiYgciXPOe5%2BjRjHGlFLWkZ%2F3KI9mzVRJaD%2BcWjY7%2B8k5eFR%2B%0ACz6GKZFv9%2Fb2%2FvCPvv4Xf%2FEX3%2FjGC1%2F96levXn2qKIrEsSgK55SZRZM5awEA5cmKt0jinNaSrauq%0AqojIe2%2FX2T60azjnj70%2Fosq3vvWtW7duWaaTiYXOiI4%2BCqYPs%2BHKV57z3gNK3%2FdmKKNoSskyWQgG%0AVpOfUpF0blPTDcJDKSIOhuffrO38DNiLsixDCIgYY2zbtu%2F7g9UlezC89%2BTdjlEAxBhjjETky8Ku%0AgKo6GAYjADBzSinGaClVdsV0DAXbJ9ttC4%2FiIU3TZMbiRzjn%2BjSYBJly29uiKPK8kXfNzCzEzM6h%0A977rm%2BPj43a7QcTEse%2F7rtuklECSOXpijN4%2F2g8YY1wsFkR0fHx85cqVv%2Fu7v3v22WdDCKmP0%2Fnq%0Acbzliy12nzFjxvtAR1HQJ2XWP7H4pPJb3EfM%2F%2FmimP0hhNPTU1vesgn7SYnrvsTIPvWsyoCzEvBz%0AmNo35%2F508%2BbNl1566ec%2F%2F%2Fnh4WFZlogupRTbmH94zkQ%2Fx1vsk8jinBNyiGjGBI4ZNapqSTEiAz8h%0AInUKExWl2ROjM%2Fg8b0HEMPIQM4by1%2FBsIGV6lPAY3pK5ik6%2BI6ktyzKl1Pf9ycnJzZs3N5t1COHp%0Aq0%2Bdm6bsgptd%2BNnjcfcXAETELqAZnczMzCYVO3cK77ORGYau695%2B%2B%2B2Dg4Oqqo6Ojux6LpfLjxo%2F%0Ax8dkE5ky0%2FtBSZVSApaUUl2XMHqdEHcmcs7Mf5i3nHs9%2BAR1J7XNkRAiMu2WRRTtc%2B%2B9JXLYX4mI%0AwNlrVe3H%2BIFOpJIi4pzHSRzPBuY0s2U6SHOtiKm5n7Pz89fMB2Hb9D6cOzv77Xa7zfQmTzXM7Hxl%0A1Mw5B1jWdS0p9n1fliUAiPREROCNrr9P3MzO2iLP6%2FW6qqo7d%2B6EEPaWK3hI9f3wzZ15y4wZX0Lk%0AufWcnfFl1S18UvUGvqzxFvNsmU1gajFz2n3ex%2FWkY7C2J%2Bb7lEg8ClnhACqUuUTs8e23bv7yF7%2B%2B%0Af%2B%2B0DAdVUa3Xp%2Bt1V9X7ADmpxV4iAAgOqSmACI4gm0rMIijqHQZwCA4UULHAUHPHzEwJATFGTOI8%0AeRVQ8cIBwYkIJxUOwkhWrwx0SNYf7ZgyDM%2BGiKgIAtC5kmN6hkox2SRzXie2M%2BZ
    --> END POST (482644-byte body)
 */
    @FormUrlEncoded
    @POST("facepp/v3/detect")
    Observable<DetectBean> getDetectBase64(
            @Field("api_key") String api_key,
            @Field("api_secret") String api_secret,
            @Field("image_base64") String image_base64
    );

/**
 * 本地图片上传
    --> POST https://api-cn.faceplusplus.com/facepp/v3/detect
    Content-Type: multipart/form-data; boundary=3292bed4-884a-4824-9d92-ea451ba8fca9
    Content-Length: 51558

    --3292bed4-884a-4824-9d92-ea451ba8fca9
    Content-Disposition: form-data; name="api_key"
    Content-Length: 32

    rv5Pab4EsmqWFft6tBKK5GqvsBGerHeb
    --3292bed4-884a-4824-9d92-ea451ba8fca9
    Content-Disposition: form-data; name="api_secret"
    Content-Length: 32

    OVNhOVgsxOhq4wsYzSj7-88ZD1BO3vHP
    --3292bed4-884a-4824-9d92-ea451ba8fca9
    Content-Disposition: form-data; name="image_file"; filename="detect.png"
    Content-Type: multipart/form-data
    Content-Length: 51049

            ������JFIF����%��%��������C��
            


            %# , #&')*)-0-(0%()(����C
            
            

            (((((((((((((((((((((((((((((((((((((((((((((((((((����6="������������������������������	����`��	��!1AQaq"R�234���#567Brt����$bsu����CSV����%���&DTFU���Ecde�����������������������������������3��������!1A"Qa�2��q����B�#3����������?�����.$�"G���C�q����Uq[����\~�h�?U\D�?�����W���ꦇ���U�K�2�ނ����4?�����O��iຓE^}\\ֿg�P�{���)�4ڀZ$���:}����_�M������z�"�OR�[i������?IYvt��V��}4�u�-�}[����T����\~����+D�ʫ4��s$�R�6+<-xcKkE3���4�}hj�z��M����5�5��,�����e��?�����W���ꦇ���U�AoC�q����K$`�����r�XI7[�O���3'�($h������������1�*o���;+^v�-�|�Z��ފ�o���<pA$��	;��5�}!q�(^�xO�&�S��Ё�O;�=�hvW*���w����f}-�2A��:^�����V:�i$���w2WF;�>��g�#����{�^s����#������U�	. ��d[_K������}�������I�{�	��c������j��di�Z����ۧ���Kt�e���6-Ǉ�?�,�8 ?�_�/��Ո� � �.q�2p|>��I�u,����`����Y���t���_:��F���/=k�2^z6�_F�+��"?�_�/��×���K��ctxնU�Å?���yn��uT�Go��P���Nƾ=%�gb�9ӡp{��r0p��o*�{�tWz��B��?}?J�>-xN�����>�ϟꅫ���������8d8�Y3<���O>� ����y���B�^O�۪@F2V�	��EE���d�z��Ao�2��BY��������������A�x���/]-c�D�cU-7K��u�yn�tg�q����2����P��\W�7������*�z�Ev�3�&_����H=�/�>
        ��K�5�Co�%�EH�����\�������G� �����q������\�B�n����W��0~�4�w������$����?��.�g��r
        ̊�4�F���T��5��v�?��{�����%��t#��[Íߗ����]��j� ��a���2��}��? ��i�5�᪏��}>�:�������\)��G�c8�)�4���ͮ��]��x~�L����=���R<��<<1���Z�q��a�?b��n]̎��C�����vV�����g���w���������k��?#���SP�����o��A�[�����������]g�"�e� Og������R惟HsYq��H���(�w	����#8x?O1������֚��$Y�5�������\��G�͏[Tz�L�I���v�2�{�e���I�����������1���߈%�Ie��wN%�$9�Ӎ��/�&ac�+���w.���&��F�N�f�����E�fx+ȑ#�G���?��Po)^Q��C#��k�\��N��8������;�x��T�{�`������?�����.�~"�����MGip�"8���F��@��_g�\^�	��N�7�lN
    --> END POST (51558-byte body)
*/
    @Multipart
    @POST("facepp/v3/detect")
    Observable<DetectBean> getDetectFile(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file,
            @Part MultipartBody.Part return_landmark,
            @Part MultipartBody.Part return_attributes
    );

}
