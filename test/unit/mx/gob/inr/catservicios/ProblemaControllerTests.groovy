package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(ProblemaController)
@Mock(Problema)
class ProblemaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/problema/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.problemaInstanceList.size() == 0
        assert model.problemaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.problemaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.problemaInstance != null
        assert view == '/problema/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/problema/show/1'
        assert controller.flash.message != null
        assert Problema.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/problema/list'

        populateValidParams(params)
        def problema = new Problema(params)

        assert problema.save() != null

        params.id = problema.id

        def model = controller.show()

        assert model.problemaInstance == problema
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/problema/list'

        populateValidParams(params)
        def problema = new Problema(params)

        assert problema.save() != null

        params.id = problema.id

        def model = controller.edit()

        assert model.problemaInstance == problema
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/problema/list'

        response.reset()

        populateValidParams(params)
        def problema = new Problema(params)

        assert problema.save() != null

        // test invalid parameters in update
        params.id = problema.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/problema/edit"
        assert model.problemaInstance != null

        problema.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/problema/show/$problema.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        problema.clearErrors()

        populateValidParams(params)
        params.id = problema.id
        params.version = -1
        controller.update()

        assert view == "/problema/edit"
        assert model.problemaInstance != null
        assert model.problemaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/problema/list'

        response.reset()

        populateValidParams(params)
        def problema = new Problema(params)

        assert problema.save() != null
        assert Problema.count() == 1

        params.id = problema.id

        controller.delete()

        assert Problema.count() == 0
        assert Problema.get(problema.id) == null
        assert response.redirectedUrl == '/problema/list'
    }
}
