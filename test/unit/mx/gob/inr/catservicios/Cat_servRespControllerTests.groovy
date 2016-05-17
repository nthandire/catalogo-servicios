package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(Cat_servRespController)
@Mock(Cat_servResp)
class Cat_servRespControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cat_servResp/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cat_servRespInstanceList.size() == 0
        assert model.cat_servRespInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cat_servRespInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cat_servRespInstance != null
        assert view == '/cat_servResp/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cat_servResp/show/1'
        assert controller.flash.message != null
        assert Cat_servResp.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servResp/list'

        populateValidParams(params)
        def cat_servResp = new Cat_servResp(params)

        assert cat_servResp.save() != null

        params.id = cat_servResp.id

        def model = controller.show()

        assert model.cat_servRespInstance == cat_servResp
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servResp/list'

        populateValidParams(params)
        def cat_servResp = new Cat_servResp(params)

        assert cat_servResp.save() != null

        params.id = cat_servResp.id

        def model = controller.edit()

        assert model.cat_servRespInstance == cat_servResp
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servResp/list'

        response.reset()

        populateValidParams(params)
        def cat_servResp = new Cat_servResp(params)

        assert cat_servResp.save() != null

        // test invalid parameters in update
        params.id = cat_servResp.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cat_servResp/edit"
        assert model.cat_servRespInstance != null

        cat_servResp.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cat_servResp/show/$cat_servResp.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cat_servResp.clearErrors()

        populateValidParams(params)
        params.id = cat_servResp.id
        params.version = -1
        controller.update()

        assert view == "/cat_servResp/edit"
        assert model.cat_servRespInstance != null
        assert model.cat_servRespInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cat_servResp/list'

        response.reset()

        populateValidParams(params)
        def cat_servResp = new Cat_servResp(params)

        assert cat_servResp.save() != null
        assert Cat_servResp.count() == 1

        params.id = cat_servResp.id

        controller.delete()

        assert Cat_servResp.count() == 0
        assert Cat_servResp.get(cat_servResp.id) == null
        assert response.redirectedUrl == '/cat_servResp/list'
    }
}
