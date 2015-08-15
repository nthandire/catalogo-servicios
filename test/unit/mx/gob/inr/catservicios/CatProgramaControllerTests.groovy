package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(CatProgramaController)
@Mock(CatPrograma)
class CatProgramaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catPrograma/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catProgramaInstanceList.size() == 0
        assert model.catProgramaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catProgramaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catProgramaInstance != null
        assert view == '/catPrograma/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catPrograma/show/1'
        assert controller.flash.message != null
        assert CatPrograma.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catPrograma/list'

        populateValidParams(params)
        def catPrograma = new CatPrograma(params)

        assert catPrograma.save() != null

        params.id = catPrograma.id

        def model = controller.show()

        assert model.catProgramaInstance == catPrograma
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catPrograma/list'

        populateValidParams(params)
        def catPrograma = new CatPrograma(params)

        assert catPrograma.save() != null

        params.id = catPrograma.id

        def model = controller.edit()

        assert model.catProgramaInstance == catPrograma
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catPrograma/list'

        response.reset()

        populateValidParams(params)
        def catPrograma = new CatPrograma(params)

        assert catPrograma.save() != null

        // test invalid parameters in update
        params.id = catPrograma.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catPrograma/edit"
        assert model.catProgramaInstance != null

        catPrograma.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catPrograma/show/$catPrograma.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catPrograma.clearErrors()

        populateValidParams(params)
        params.id = catPrograma.id
        params.version = -1
        controller.update()

        assert view == "/catPrograma/edit"
        assert model.catProgramaInstance != null
        assert model.catProgramaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catPrograma/list'

        response.reset()

        populateValidParams(params)
        def catPrograma = new CatPrograma(params)

        assert catPrograma.save() != null
        assert CatPrograma.count() == 1

        params.id = catPrograma.id

        controller.delete()

        assert CatPrograma.count() == 0
        assert CatPrograma.get(catPrograma.id) == null
        assert response.redirectedUrl == '/catPrograma/list'
    }
}
