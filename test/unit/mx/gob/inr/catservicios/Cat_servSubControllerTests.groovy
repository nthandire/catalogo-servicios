package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(Cat_servSubController)
@Mock(Cat_servSub)
class Cat_servSubControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cat_servSub/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cat_servSubInstanceList.size() == 0
        assert model.cat_servSubInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cat_servSubInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cat_servSubInstance != null
        assert view == '/cat_servSub/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cat_servSub/show/1'
        assert controller.flash.message != null
        assert Cat_servSub.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servSub/list'

        populateValidParams(params)
        def cat_servSub = new Cat_servSub(params)

        assert cat_servSub.save() != null

        params.id = cat_servSub.id

        def model = controller.show()

        assert model.cat_servSubInstance == cat_servSub
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servSub/list'

        populateValidParams(params)
        def cat_servSub = new Cat_servSub(params)

        assert cat_servSub.save() != null

        params.id = cat_servSub.id

        def model = controller.edit()

        assert model.cat_servSubInstance == cat_servSub
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servSub/list'

        response.reset()

        populateValidParams(params)
        def cat_servSub = new Cat_servSub(params)

        assert cat_servSub.save() != null

        // test invalid parameters in update
        params.id = cat_servSub.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cat_servSub/edit"
        assert model.cat_servSubInstance != null

        cat_servSub.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cat_servSub/show/$cat_servSub.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cat_servSub.clearErrors()

        populateValidParams(params)
        params.id = cat_servSub.id
        params.version = -1
        controller.update()

        assert view == "/cat_servSub/edit"
        assert model.cat_servSubInstance != null
        assert model.cat_servSubInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cat_servSub/list'

        response.reset()

        populateValidParams(params)
        def cat_servSub = new Cat_servSub(params)

        assert cat_servSub.save() != null
        assert Cat_servSub.count() == 1

        params.id = cat_servSub.id

        controller.delete()

        assert Cat_servSub.count() == 0
        assert Cat_servSub.get(cat_servSub.id) == null
        assert response.redirectedUrl == '/cat_servSub/list'
    }
}
