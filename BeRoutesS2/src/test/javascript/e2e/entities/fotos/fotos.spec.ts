import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FotosComponentsPage, FotosDeleteDialog, FotosUpdatePage } from './fotos.page-object';

const expect = chai.expect;

describe('Fotos e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let fotosComponentsPage: FotosComponentsPage;
  let fotosUpdatePage: FotosUpdatePage;
  let fotosDeleteDialog: FotosDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Fotos', async () => {
    await navBarPage.goToEntity('fotos');
    fotosComponentsPage = new FotosComponentsPage();
    await browser.wait(ec.visibilityOf(fotosComponentsPage.title), 5000);
    expect(await fotosComponentsPage.getTitle()).to.eq('Fotos');
    await browser.wait(ec.or(ec.visibilityOf(fotosComponentsPage.entities), ec.visibilityOf(fotosComponentsPage.noResult)), 1000);
  });

  it('should load create Fotos page', async () => {
    await fotosComponentsPage.clickOnCreateButton();
    fotosUpdatePage = new FotosUpdatePage();
    expect(await fotosUpdatePage.getPageTitle()).to.eq('Create or edit a Fotos');
    await fotosUpdatePage.cancel();
  });

  it('should create and save Fotos', async () => {
    const nbButtonsBeforeCreate = await fotosComponentsPage.countDeleteButtons();

    await fotosComponentsPage.clickOnCreateButton();

    await promise.all([fotosUpdatePage.setTituloInput('titulo'), fotosUpdatePage.setUrlInput('url')]);

    expect(await fotosUpdatePage.getTituloInput()).to.eq('titulo', 'Expected Titulo value to be equals to titulo');
    expect(await fotosUpdatePage.getUrlInput()).to.eq('url', 'Expected Url value to be equals to url');

    await fotosUpdatePage.save();
    expect(await fotosUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await fotosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Fotos', async () => {
    const nbButtonsBeforeDelete = await fotosComponentsPage.countDeleteButtons();
    await fotosComponentsPage.clickOnLastDeleteButton();

    fotosDeleteDialog = new FotosDeleteDialog();
    expect(await fotosDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Fotos?');
    await fotosDeleteDialog.clickOnConfirmButton();

    expect(await fotosComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
