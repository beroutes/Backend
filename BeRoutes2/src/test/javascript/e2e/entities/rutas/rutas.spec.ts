import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RutasComponentsPage, RutasDeleteDialog, RutasUpdatePage } from './rutas.page-object';

const expect = chai.expect;

describe('Rutas e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rutasComponentsPage: RutasComponentsPage;
  let rutasUpdatePage: RutasUpdatePage;
  let rutasDeleteDialog: RutasDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Rutas', async () => {
    await navBarPage.goToEntity('rutas');
    rutasComponentsPage = new RutasComponentsPage();
    await browser.wait(ec.visibilityOf(rutasComponentsPage.title), 5000);
    expect(await rutasComponentsPage.getTitle()).to.eq('Rutas');
    await browser.wait(ec.or(ec.visibilityOf(rutasComponentsPage.entities), ec.visibilityOf(rutasComponentsPage.noResult)), 1000);
  });

  it('should load create Rutas page', async () => {
    await rutasComponentsPage.clickOnCreateButton();
    rutasUpdatePage = new RutasUpdatePage();
    expect(await rutasUpdatePage.getPageTitle()).to.eq('Create or edit a Rutas');
    await rutasUpdatePage.cancel();
  });

  it('should create and save Rutas', async () => {
    const nbButtonsBeforeCreate = await rutasComponentsPage.countDeleteButtons();

    await rutasComponentsPage.clickOnCreateButton();

    await promise.all([
      rutasUpdatePage.setTituloInput('titulo'),
      rutasUpdatePage.setDuracionInput('5'),
      rutasUpdatePage.setTemporadaInput('temporada'),
      rutasUpdatePage.setPresupuestoInput('5'),
      rutasUpdatePage.setDescripcionInput('descripcion')
    ]);

    expect(await rutasUpdatePage.getTituloInput()).to.eq('titulo', 'Expected Titulo value to be equals to titulo');
    expect(await rutasUpdatePage.getDuracionInput()).to.eq('5', 'Expected duracion value to be equals to 5');
    expect(await rutasUpdatePage.getTemporadaInput()).to.eq('temporada', 'Expected Temporada value to be equals to temporada');
    expect(await rutasUpdatePage.getPresupuestoInput()).to.eq('5', 'Expected presupuesto value to be equals to 5');
    expect(await rutasUpdatePage.getDescripcionInput()).to.eq('descripcion', 'Expected Descripcion value to be equals to descripcion');

    await rutasUpdatePage.save();
    expect(await rutasUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rutasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Rutas', async () => {
    const nbButtonsBeforeDelete = await rutasComponentsPage.countDeleteButtons();
    await rutasComponentsPage.clickOnLastDeleteButton();

    rutasDeleteDialog = new RutasDeleteDialog();
    expect(await rutasDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Rutas?');
    await rutasDeleteDialog.clickOnConfirmButton();

    expect(await rutasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
