package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(IncidenteLaboratorioController)
@Mock(IncidenteLaboratorio)
class IncidenteLaboratorioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/incidenteLaboratorio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.incidenteLaboratorioInstanceList.size() == 0
        assert model.incidenteLaboratorioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.incidenteLaboratorioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.incidenteLaboratorioInstance != null
        assert view == '/incidenteLaboratorio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/incidenteLaboratorio/show/1'
        assert controller.flash.message != null
        assert IncidenteLaboratorio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteLaboratorio/list'

        populateValidParams(params)
        def incidenteLaboratorio = new IncidenteLaboratorio(params)

        assert incidenteLaboratorio.save() != null

        params.id = incidenteLaboratorio.id

        def model = controller.show()

        assert model.incidenteLaboratorioInstance == incidenteLaboratorio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteLaboratorio/list'

        populateValidParams(params)
        def incidenteLaboratorio = new IncidenteLaboratorio(params)

        assert incidenteLaboratorio.save() != null

        params.id = incidenteLaboratorio.id

        def model = controller.edit()

        assert model.incidenteLaboratorioInstance == incidenteLaboratorio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/incidenteLaboratorio/list'

        response.reset()

        populateValidParams(params)
        def incidenteLaboratorio = new IncidenteLaboratorio(params)

        assert incidenteLaboratorio.save() != null

        // test invalid parameters in update
        params.id = incidenteLaboratorio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/incidenteLaboratorio/edit"
        assert model.incidenteLaboratorioInstance != null

        incidenteLaboratorio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/incidenteLaboratorio/show/$incidenteLaboratorio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        incidenteLaboratorio.clearErrors()

        populateValidParams(params)
        params.id = incidenteLaboratorio.id
        params.version = -1
        controller.update()

        assert view == "/incidenteLaboratorio/edit"
        assert model.incidenteLaboratorioInstance != null
        assert model.incidenteLaboratorioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/incidenteLaboratorio/list'

        response.reset()

        populateValidParams(params)
        def incidenteLaboratorio = new IncidenteLaboratorio(params)

        assert incidenteLaboratorio.save() != null
        assert IncidenteLaboratorio.count() == 1

        params.id = incidenteLaboratorio.id

        controller.delete()

        assert IncidenteLaboratorio.count() == 0
        assert IncidenteLaboratorio.get(incidenteLaboratorio.id) == null
        assert response.redirectedUrl == '/incidenteLaboratorio/list'
    }
}
