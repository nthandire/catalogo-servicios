package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(MonitoreoController)
@Mock(Monitoreo)
class MonitoreoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/monitoreo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.monitoreoInstanceList.size() == 0
        assert model.monitoreoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.monitoreoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.monitoreoInstance != null
        assert view == '/monitoreo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/monitoreo/show/1'
        assert controller.flash.message != null
        assert Monitoreo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/monitoreo/list'

        populateValidParams(params)
        def monitoreo = new Monitoreo(params)

        assert monitoreo.save() != null

        params.id = monitoreo.id

        def model = controller.show()

        assert model.monitoreoInstance == monitoreo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/monitoreo/list'

        populateValidParams(params)
        def monitoreo = new Monitoreo(params)

        assert monitoreo.save() != null

        params.id = monitoreo.id

        def model = controller.edit()

        assert model.monitoreoInstance == monitoreo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/monitoreo/list'

        response.reset()

        populateValidParams(params)
        def monitoreo = new Monitoreo(params)

        assert monitoreo.save() != null

        // test invalid parameters in update
        params.id = monitoreo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/monitoreo/edit"
        assert model.monitoreoInstance != null

        monitoreo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/monitoreo/show/$monitoreo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        monitoreo.clearErrors()

        populateValidParams(params)
        params.id = monitoreo.id
        params.version = -1
        controller.update()

        assert view == "/monitoreo/edit"
        assert model.monitoreoInstance != null
        assert model.monitoreoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/monitoreo/list'

        response.reset()

        populateValidParams(params)
        def monitoreo = new Monitoreo(params)

        assert monitoreo.save() != null
        assert Monitoreo.count() == 1

        params.id = monitoreo.id

        controller.delete()

        assert Monitoreo.count() == 0
        assert Monitoreo.get(monitoreo.id) == null
        assert response.redirectedUrl == '/monitoreo/list'
    }
}
