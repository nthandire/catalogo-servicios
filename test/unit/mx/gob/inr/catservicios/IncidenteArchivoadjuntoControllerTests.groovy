package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(IncidenteArchivoadjuntoController)
@Mock(IncidenteArchivoadjunto)
class IncidenteArchivoadjuntoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/incidenteArchivoadjunto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.incidenteArchivoadjuntoInstanceList.size() == 0
        assert model.incidenteArchivoadjuntoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.incidenteArchivoadjuntoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.incidenteArchivoadjuntoInstance != null
        assert view == '/incidenteArchivoadjunto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/incidenteArchivoadjunto/show/1'
        assert controller.flash.message != null
        assert IncidenteArchivoadjunto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteArchivoadjunto/list'

        populateValidParams(params)
        def incidenteArchivoadjunto = new IncidenteArchivoadjunto(params)

        assert incidenteArchivoadjunto.save() != null

        params.id = incidenteArchivoadjunto.id

        def model = controller.show()

        assert model.incidenteArchivoadjuntoInstance == incidenteArchivoadjunto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteArchivoadjunto/list'

        populateValidParams(params)
        def incidenteArchivoadjunto = new IncidenteArchivoadjunto(params)

        assert incidenteArchivoadjunto.save() != null

        params.id = incidenteArchivoadjunto.id

        def model = controller.edit()

        assert model.incidenteArchivoadjuntoInstance == incidenteArchivoadjunto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteArchivoadjunto/list'

        response.reset()

        populateValidParams(params)
        def incidenteArchivoadjunto = new IncidenteArchivoadjunto(params)

        assert incidenteArchivoadjunto.save() != null

        // test invalid parameters in update
        params.id = incidenteArchivoadjunto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/incidenteArchivoadjunto/edit"
        assert model.incidenteArchivoadjuntoInstance != null

        incidenteArchivoadjunto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/incidenteArchivoadjunto/show/$incidenteArchivoadjunto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        incidenteArchivoadjunto.clearErrors()

        populateValidParams(params)
        params.id = incidenteArchivoadjunto.id
        params.version = -1
        controller.update()

        assert view == "/incidenteArchivoadjunto/edit"
        assert model.incidenteArchivoadjuntoInstance != null
        assert model.incidenteArchivoadjuntoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/incidenteArchivoadjunto/list'

        response.reset()

        populateValidParams(params)
        def incidenteArchivoadjunto = new IncidenteArchivoadjunto(params)

        assert incidenteArchivoadjunto.save() != null
        assert IncidenteArchivoadjunto.count() == 1

        params.id = incidenteArchivoadjunto.id

        controller.delete()

        assert IncidenteArchivoadjunto.count() == 0
        assert IncidenteArchivoadjunto.get(incidenteArchivoadjunto.id) == null
        assert response.redirectedUrl == '/incidenteArchivoadjunto/list'
    }
}
