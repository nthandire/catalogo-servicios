package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(IncidenteController)
@Mock(Incidente)
class IncidenteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/incidente/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.incidenteInstanceList.size() == 0
        assert model.incidenteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.incidenteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.incidenteInstance != null
        assert view == '/incidente/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/incidente/show/1'
        assert controller.flash.message != null
        assert Incidente.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/incidente/list'

        populateValidParams(params)
        def incidente = new Incidente(params)

        assert incidente.save() != null

        params.id = incidente.id

        def model = controller.show()

        assert model.incidenteInstance == incidente
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/incidente/list'

        populateValidParams(params)
        def incidente = new Incidente(params)

        assert incidente.save() != null

        params.id = incidente.id

        def model = controller.edit()

        assert model.incidenteInstance == incidente
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/incidente/list'

        response.reset()

        populateValidParams(params)
        def incidente = new Incidente(params)

        assert incidente.save() != null

        // test invalid parameters in update
        params.id = incidente.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/incidente/edit"
        assert model.incidenteInstance != null

        incidente.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/incidente/show/$incidente.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        incidente.clearErrors()

        populateValidParams(params)
        params.id = incidente.id
        params.version = -1
        controller.update()

        assert view == "/incidente/edit"
        assert model.incidenteInstance != null
        assert model.incidenteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/incidente/list'

        response.reset()

        populateValidParams(params)
        def incidente = new Incidente(params)

        assert incidente.save() != null
        assert Incidente.count() == 1

        params.id = incidente.id

        controller.delete()

        assert Incidente.count() == 0
        assert Incidente.get(incidente.id) == null
        assert response.redirectedUrl == '/incidente/list'
    }
}
