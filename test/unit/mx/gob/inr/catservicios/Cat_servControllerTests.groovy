package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(Cat_servController)
@Mock(Cat_serv)
class Cat_servControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cat_serv/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cat_servInstanceList.size() == 0
        assert model.cat_servInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cat_servInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cat_servInstance != null
        assert view == '/cat_serv/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cat_serv/show/1'
        assert controller.flash.message != null
        assert Cat_serv.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_serv/list'

        populateValidParams(params)
        def cat_serv = new Cat_serv(params)

        assert cat_serv.save() != null

        params.id = cat_serv.id

        def model = controller.show()

        assert model.cat_servInstance == cat_serv
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_serv/list'

        populateValidParams(params)
        def cat_serv = new Cat_serv(params)

        assert cat_serv.save() != null

        params.id = cat_serv.id

        def model = controller.edit()

        assert model.cat_servInstance == cat_serv
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_serv/list'

        response.reset()

        populateValidParams(params)
        def cat_serv = new Cat_serv(params)

        assert cat_serv.save() != null

        // test invalid parameters in update
        params.id = cat_serv.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cat_serv/edit"
        assert model.cat_servInstance != null

        cat_serv.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cat_serv/show/$cat_serv.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cat_serv.clearErrors()

        populateValidParams(params)
        params.id = cat_serv.id
        params.version = -1
        controller.update()

        assert view == "/cat_serv/edit"
        assert model.cat_servInstance != null
        assert model.cat_servInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cat_serv/list'

        response.reset()

        populateValidParams(params)
        def cat_serv = new Cat_serv(params)

        assert cat_serv.save() != null
        assert Cat_serv.count() == 1

        params.id = cat_serv.id

        controller.delete()

        assert Cat_serv.count() == 0
        assert Cat_serv.get(cat_serv.id) == null
        assert response.redirectedUrl == '/cat_serv/list'
    }
}
