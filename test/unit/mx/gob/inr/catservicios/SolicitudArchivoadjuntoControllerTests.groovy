package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(SolicitudArchivoadjuntoController)
@Mock(SolicitudArchivoadjunto)
class SolicitudArchivoadjuntoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitudArchivoadjunto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitudArchivoadjuntoInstanceList.size() == 0
        assert model.solicitudArchivoadjuntoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.solicitudArchivoadjuntoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.solicitudArchivoadjuntoInstance != null
        assert view == '/solicitudArchivoadjunto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitudArchivoadjunto/show/1'
        assert controller.flash.message != null
        assert SolicitudArchivoadjunto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudArchivoadjunto/list'

        populateValidParams(params)
        def solicitudArchivoadjunto = new SolicitudArchivoadjunto(params)

        assert solicitudArchivoadjunto.save() != null

        params.id = solicitudArchivoadjunto.id

        def model = controller.show()

        assert model.solicitudArchivoadjuntoInstance == solicitudArchivoadjunto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudArchivoadjunto/list'

        populateValidParams(params)
        def solicitudArchivoadjunto = new SolicitudArchivoadjunto(params)

        assert solicitudArchivoadjunto.save() != null

        params.id = solicitudArchivoadjunto.id

        def model = controller.edit()

        assert model.solicitudArchivoadjuntoInstance == solicitudArchivoadjunto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudArchivoadjunto/list'

        response.reset()

        populateValidParams(params)
        def solicitudArchivoadjunto = new SolicitudArchivoadjunto(params)

        assert solicitudArchivoadjunto.save() != null

        // test invalid parameters in update
        params.id = solicitudArchivoadjunto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitudArchivoadjunto/edit"
        assert model.solicitudArchivoadjuntoInstance != null

        solicitudArchivoadjunto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitudArchivoadjunto/show/$solicitudArchivoadjunto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitudArchivoadjunto.clearErrors()

        populateValidParams(params)
        params.id = solicitudArchivoadjunto.id
        params.version = -1
        controller.update()

        assert view == "/solicitudArchivoadjunto/edit"
        assert model.solicitudArchivoadjuntoInstance != null
        assert model.solicitudArchivoadjuntoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitudArchivoadjunto/list'

        response.reset()

        populateValidParams(params)
        def solicitudArchivoadjunto = new SolicitudArchivoadjunto(params)

        assert solicitudArchivoadjunto.save() != null
        assert SolicitudArchivoadjunto.count() == 1

        params.id = solicitudArchivoadjunto.id

        controller.delete()

        assert SolicitudArchivoadjunto.count() == 0
        assert SolicitudArchivoadjunto.get(solicitudArchivoadjunto.id) == null
        assert response.redirectedUrl == '/solicitudArchivoadjunto/list'
    }
}
