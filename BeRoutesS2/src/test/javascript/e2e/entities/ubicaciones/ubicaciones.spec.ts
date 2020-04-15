import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UbicacionesComponentsPage, UbicacionesDeleteDialog, UbicacionesUpdatePage } from './ubicaciones.page-object';

const expect = chai.expect;

describe('Ubicaciones e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let ubicacionesComponentsPage: UbicacionesComponentsPage;
  let ubicacionesUpdatePage: UbicacionesUpdatePage;
  let ubicacionesDeleteDialog: UbicacionesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Ubicaciones', async () => {
    await navBarPage.goToEntity('ubicaciones');
    ubicacionesComponentsPage = new UbicacionesComponentsPage();
    await browser.wait(ec.visibilityOf(ubicacionesComponentsPage.title), 5000);
    expect(await ubicacionesComponentsPage.getTitle()).to.eq('Ubicaciones');
    await browser.wait(
      ec.or(ec.visibilityOf(ubicacionesComponentsPage.entities), ec.visibilityOf(ubicacionesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Ubicaciones page', async () => {
    await ubicacionesComponentsPage.clickOnCreateButton();
    ubicacionesUpdatePage = new UbicacionesUpdatePage();
    expect(await ubicacionesUpdatePage.getPageTitle()).to.eq('Create or edit a Ubicaciones');
    await ubicacionesUpdatePage.cancel();
  });

  it('should create and save Ubicaciones', async () => {
    const nbButtonsBeforeCreate = await ubicacionesComponentsPage.countDeleteButtons();

    await ubicacionesComponentsPage.clickOnCreateButton();

    await promise.all([
      ubicacionesUpdatePage.setTituloInput('titulo'),
      ubicacionesUpdatePage.setDescripcionInput('descripcion'),
      ubicacionesUpdatePage.setCoordenadaXInput('5'),
      ubicacionesUpdatePage.setCoordenadaYInput('5'),
      ubicacionesUpdatePage.setDuracionInput('5'),
      ubicacionesUpdatePage.setQrInput('qr')
    ]);

    expect(await ubicacionesUpdatePage.getTituloInput()).to.eq('titulo', 'Expected Titulo value to be equals to titulo');
    expect(await ubicacionesUpdatePage.getDescripcionInput()).to.eq(
      'descripcion',
      'Expected Descripcion value to be equals to descripcion'
    );
    expect(await ubicacionesUpdatePage.getCoordenadaXInput()).to.eq('5', 'Expected coordenadaX value to be equals to 5');
    expect(await ubicacionesUpdatePage.getCoordenadaYInput()).to.eq('5', 'Expected coordenadaY value to be equals to 5');
    expect(await ubicacionesUpdatePage.getDuracionInput()).to.eq('5', 'Expected duracion value to be equals to 5');
    expect(await ubicacionesUpdatePage.getQrInput()).to.eq('qr', 'Expected Qr value to be equals to qr');

    await ubicacionesUpdatePage.save();
    expect(await ubicacionesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await ubicacionesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Ubicaciones', async () => {
    const nbButtonsBeforeDelete = await ubicacionesComponentsPage.countDeleteButtons();
    await ubicacionesComponentsPage.clickOnLastDeleteButton();

    ubicacionesDeleteDialog = new UbicacionesDeleteDialog();
    expect(await ubicacionesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Ubicaciones?');
    await ubicacionesDeleteDialog.clickOnConfirmButton();

    expect(await ubicacionesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
