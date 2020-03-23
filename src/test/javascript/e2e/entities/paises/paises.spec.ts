import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PaisesComponentsPage, PaisesDeleteDialog, PaisesUpdatePage } from './paises.page-object';

const expect = chai.expect;

describe('Paises e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paisesComponentsPage: PaisesComponentsPage;
  let paisesUpdatePage: PaisesUpdatePage;
  let paisesDeleteDialog: PaisesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Paises', async () => {
    await navBarPage.goToEntity('paises');
    paisesComponentsPage = new PaisesComponentsPage();
    await browser.wait(ec.visibilityOf(paisesComponentsPage.title), 5000);
    expect(await paisesComponentsPage.getTitle()).to.eq('Paises');
    await browser.wait(ec.or(ec.visibilityOf(paisesComponentsPage.entities), ec.visibilityOf(paisesComponentsPage.noResult)), 1000);
  });

  it('should load create Paises page', async () => {
    await paisesComponentsPage.clickOnCreateButton();
    paisesUpdatePage = new PaisesUpdatePage();
    expect(await paisesUpdatePage.getPageTitle()).to.eq('Create or edit a Paises');
    await paisesUpdatePage.cancel();
  });

  it('should create and save Paises', async () => {
    const nbButtonsBeforeCreate = await paisesComponentsPage.countDeleteButtons();

    await paisesComponentsPage.clickOnCreateButton();

    await promise.all([paisesUpdatePage.setNombreInput('nombre')]);

    expect(await paisesUpdatePage.getNombreInput()).to.eq('nombre', 'Expected Nombre value to be equals to nombre');

    await paisesUpdatePage.save();
    expect(await paisesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await paisesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Paises', async () => {
    const nbButtonsBeforeDelete = await paisesComponentsPage.countDeleteButtons();
    await paisesComponentsPage.clickOnLastDeleteButton();

    paisesDeleteDialog = new PaisesDeleteDialog();
    expect(await paisesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Paises?');
    await paisesDeleteDialog.clickOnConfirmButton();

    expect(await paisesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
