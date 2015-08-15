package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(CatSistemaController)
@Mock(CatSistema)
class CatSistemaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catSistema/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catSistemaInstanceList.size() == 0
        assert model.catSistemaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catSistemaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catSistemaInstance != null
        assert view == '/catSistema/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catSistema/show/1'
        assert controller.flash.message != null
        assert CatSistema.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catSistema/list'

        populateValidParams(params)
        def catSistema = new CatSistema(params)

        assert catSistema.save() != null

        params.id = catSistema.id

        def model = controller.show()

        assert model.catSistemaInstance == catSistema
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catSistema/list'

        populateValidParams(params)
        def catSistema = new CatSistema(params)

        assert catSistema.save() != null

        params.id = catSistema.id

        def model = controller.edit()

        assert model.catSistemaInstance == catSistema
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catSistema/list'

        response.reset()

        populateValidParams(params)
        def catSistema = new CatSistema(params)

        assert catSistema.save() != null

        // test invalid parameters in update
        params.id = catSistema.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catSistema/edit"
        assert model.catSistemaInstance != null

        catSistema.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catSistema/show/$catSistema.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catSistema.clearErrors()

        populateValidParams(params)
        params.id = catSistema.id
        params.version = -1
        controller.update()

        assert view == "/catSistema/edit"
        assert model.catSistemaInstance != null
        assert model.catSistemaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catSistema/list'

        response.reset()

        populateValidParams(params)
        def catSistema = new CatSistema(params)

        assert catSistema.save() != null
        assert CatSistema.count() == 1

        params.id = catSistema.id

        controller.delete()

        assert CatSistema.count() == 0
        assert CatSistema.get(catSistema.id) == null
        assert response.redirectedUrl == '/catSistema/list'
    }
}
