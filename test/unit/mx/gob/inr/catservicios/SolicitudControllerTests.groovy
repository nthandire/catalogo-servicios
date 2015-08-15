package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(SolicitudController)
@Mock(Solicitud)
class SolicitudControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitud/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitudInstanceList.size() == 0
        assert model.solicitudInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.solicitudInstance != null
    }

    void testSave() {
        controller.save()

        assert model.solicitudInstance != null
        assert view == '/solicitud/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitud/show/1'
        assert controller.flash.message != null
        assert Solicitud.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitud/list'

        populateValidParams(params)
        def solicitud = new Solicitud(params)

        assert solicitud.save() != null

        params.id = solicitud.id

        def model = controller.show()

        assert model.solicitudInstance == solicitud
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitud/list'

        populateValidParams(params)
        def solicitud = new Solicitud(params)

        assert solicitud.save() != null

        params.id = solicitud.id

        def model = controller.edit()

        assert model.solicitudInstance == solicitud
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitud/list'

        response.reset()

        populateValidParams(params)
        def solicitud = new Solicitud(params)

        assert solicitud.save() != null

        // test invalid parameters in update
        params.id = solicitud.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitud/edit"
        assert model.solicitudInstance != null

        solicitud.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitud/show/$solicitud.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitud.clearErrors()

        populateValidParams(params)
        params.id = solicitud.id
        params.version = -1
        controller.update()

        assert view == "/solicitud/edit"
        assert model.solicitudInstance != null
        assert model.solicitudInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitud/list'

        response.reset()

        populateValidParams(params)
        def solicitud = new Solicitud(params)

        assert solicitud.save() != null
        assert Solicitud.count() == 1

        params.id = solicitud.id

        controller.delete()

        assert Solicitud.count() == 0
        assert Solicitud.get(solicitud.id) == null
        assert response.redirectedUrl == '/solicitud/list'
    }
}
