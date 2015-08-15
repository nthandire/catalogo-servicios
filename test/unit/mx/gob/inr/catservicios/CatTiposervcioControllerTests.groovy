package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(CatTiposervcioController)
@Mock(CatTiposervcio)
class CatTiposervcioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/catTiposervcio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.catTiposervcioInstanceList.size() == 0
        assert model.catTiposervcioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.catTiposervcioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.catTiposervcioInstance != null
        assert view == '/catTiposervcio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/catTiposervcio/show/1'
        assert controller.flash.message != null
        assert CatTiposervcio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/catTiposervcio/list'

        populateValidParams(params)
        def catTiposervcio = new CatTiposervcio(params)

        assert catTiposervcio.save() != null

        params.id = catTiposervcio.id

        def model = controller.show()

        assert model.catTiposervcioInstance == catTiposervcio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/catTiposervcio/list'

        populateValidParams(params)
        def catTiposervcio = new CatTiposervcio(params)

        assert catTiposervcio.save() != null

        params.id = catTiposervcio.id

        def model = controller.edit()

        assert model.catTiposervcioInstance == catTiposervcio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/catTiposervcio/list'

        response.reset()

        populateValidParams(params)
        def catTiposervcio = new CatTiposervcio(params)

        assert catTiposervcio.save() != null

        // test invalid parameters in update
        params.id = catTiposervcio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/catTiposervcio/edit"
        assert model.catTiposervcioInstance != null

        catTiposervcio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/catTiposervcio/show/$catTiposervcio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        catTiposervcio.clearErrors()

        populateValidParams(params)
        params.id = catTiposervcio.id
        params.version = -1
        controller.update()

        assert view == "/catTiposervcio/edit"
        assert model.catTiposervcioInstance != null
        assert model.catTiposervcioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/catTiposervcio/list'

        response.reset()

        populateValidParams(params)
        def catTiposervcio = new CatTiposervcio(params)

        assert catTiposervcio.save() != null
        assert CatTiposervcio.count() == 1

        params.id = catTiposervcio.id

        controller.delete()

        assert CatTiposervcio.count() == 0
        assert CatTiposervcio.get(catTiposervcio.id) == null
        assert response.redirectedUrl == '/catTiposervcio/list'
    }
}
