package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(CatEstadoController)
@Mock(CatEstado)
class CatEstadoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catEstado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catEstadoInstanceList.size() == 0
        assert model.catEstadoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catEstadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catEstadoInstance != null
        assert view == '/catEstado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catEstado/show/1'
        assert controller.flash.message != null
        assert CatEstado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catEstado/list'

        populateValidParams(params)
        def catEstado = new CatEstado(params)

        assert catEstado.save() != null

        params.id = catEstado.id

        def model = controller.show()

        assert model.catEstadoInstance == catEstado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catEstado/list'

        populateValidParams(params)
        def catEstado = new CatEstado(params)

        assert catEstado.save() != null

        params.id = catEstado.id

        def model = controller.edit()

        assert model.catEstadoInstance == catEstado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catEstado/list'

        response.reset()

        populateValidParams(params)
        def catEstado = new CatEstado(params)

        assert catEstado.save() != null

        // test invalid parameters in update
        params.id = catEstado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catEstado/edit"
        assert model.catEstadoInstance != null

        catEstado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catEstado/show/$catEstado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catEstado.clearErrors()

        populateValidParams(params)
        params.id = catEstado.id
        params.version = -1
        controller.update()

        assert view == "/catEstado/edit"
        assert model.catEstadoInstance != null
        assert model.catEstadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catEstado/list'

        response.reset()

        populateValidParams(params)
        def catEstado = new CatEstado(params)

        assert catEstado.save() != null
        assert CatEstado.count() == 1

        params.id = catEstado.id

        controller.delete()

        assert CatEstado.count() == 0
        assert CatEstado.get(catEstado.id) == null
        assert response.redirectedUrl == '/catEstado/list'
    }
}
